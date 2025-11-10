package com.noy.web.login.controller;

// ==================== Java EE导入 ====================
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// ==================== Java标准库导入 ====================
import java.util.regex.Pattern;

// ==================== Apache Commons导入 ====================
import org.apache.commons.lang3.StringUtils;

// ==================== Spring框架导入 ====================
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// ==================== 项目内部导入 ====================
import com.noy.utils.ResultUtils;
import com.noy.utils.ResultVo;
import com.noy.web.login.entity.ResetByContactParam;
import com.noy.web.member.entity.Member;
import com.noy.web.member.service.MemberService;
import com.noy.web.sys_user.entity.SysUser;
import com.noy.web.sys_user.service.SysUserService;

/**
 * 密码重置控制器（忘记密码 - 方案A）
 *
 * <p>提供基于会员号和手机号校验的密码重置接口，不发送验证码，依赖图形验证码与联系方式匹配。</p>
 *
 * <p>接口路径：/api/password/resetByContact</p>
 */
@RestController
@RequestMapping("/api/password")
public class PasswordController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysUserService sysUserService;

    private static final Pattern NON_DIGIT = Pattern.compile("[^0-9]");

    /**
     * 忘记密码：根据会员号 + 手机号 + 图形验证码，设置新密码
     */
    @PostMapping("/resetByContact")
    public ResultVo resetByContact(HttpServletRequest request, @RequestBody ResetByContactParam param) {
        // 基本必填校验
        if (StringUtils.isBlank(param.getMemberId()) ||
                StringUtils.isBlank(param.getPhone()) ||
                StringUtils.isBlank(param.getCode()) ||
                StringUtils.isBlank(param.getNewPassword()) ||
                StringUtils.isBlank(param.getConfirmPassword())) {
            return ResultUtils.error("账号/会员号、手机号、验证码和新密码不能为空！");
        }

        // 校验验证码
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");
        if (StringUtils.isBlank(code)) {
            return ResultUtils.error("验证码过期！");
        }
        if (!code.equals(param.getCode())) {
            return ResultUtils.error("验证码错误！");
        }

        // 校验密码强度与一致性（最少6位）
        if (param.getNewPassword().length() < 6) {
            return ResultUtils.error("密码长度不能少于6位！");
        }
        if (!param.getNewPassword().equals(param.getConfirmPassword())) {
            return ResultUtils.error("两次输入的密码不一致！");
        }

        // 判断用户类型：默认按会员处理
        String userType = StringUtils.defaultString(param.getUserType(), "1");
        if ("1".equals(userType)) {
            // 会员：支持会员ID（数字）或会员账号（用户名）
            Member dbMember = null;
            Long memberId = null;
            try {
                memberId = Long.parseLong(param.getMemberId());
                dbMember = memberService.getById(memberId);
            } catch (NumberFormatException ignore) {
                // 非数字：按用户名查询
                dbMember = memberService.loadUser(param.getMemberId());
                if (dbMember != null) {
                    memberId = dbMember.getMemberId();
                }
            }

            if (dbMember == null || memberId == null) {
                return ResultUtils.error("信息校验失败，请检查输入是否正确！");
            }

            // 手机号匹配（去除非数字字符后比较）
            String inputPhone = NON_DIGIT.matcher(param.getPhone()).replaceAll("");
            String dbPhone = StringUtils.defaultString(dbMember.getPhone());
            dbPhone = NON_DIGIT.matcher(dbPhone).replaceAll("");

            if (StringUtils.isBlank(inputPhone) || !inputPhone.equals(dbPhone)) {
                return ResultUtils.error("信息校验失败，请检查输入是否正确！");
            }

            // 更新会员密码
            Member update = new Member();
            update.setMemberId(memberId);
            update.setPassword(passwordEncoder.encode(param.getNewPassword()));
            memberService.updateById(update);
            return ResultUtils.success("密码重置成功！");
        } else if ("2".equals(userType)) {
            // 员工：按用户名匹配 + 手机号校验
            SysUser sysUser = sysUserService.loadUser(param.getMemberId());
            if (sysUser == null) {
                return ResultUtils.error("信息校验失败，请检查输入是否正确！");
            }

            String inputPhone = NON_DIGIT.matcher(param.getPhone()).replaceAll("");
            String dbPhone = StringUtils.defaultString(sysUser.getPhone());
            dbPhone = NON_DIGIT.matcher(dbPhone).replaceAll("");

            if (StringUtils.isBlank(inputPhone) || !inputPhone.equals(dbPhone)) {
                return ResultUtils.error("信息校验失败，请检查输入是否正确！");
            }

            SysUser update = new SysUser();
            update.setUserId(sysUser.getUserId());
            update.setPassword(passwordEncoder.encode(param.getNewPassword()));
            sysUserService.updateById(update);
            return ResultUtils.success("密码重置成功！");
        } else {
            return ResultUtils.error("用户类型错误！");
        }
    }
}