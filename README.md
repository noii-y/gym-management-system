#  斑马健身房运营管理平台 (BM Gymnasium Management System)

## 📋 项目简介

这是一个基于前后端分离架构的现代化健身房管理系统，提供会员管理、课程管理、器材管理、商品销售、失物招领等全方位的健身房运营管理功能。系统采用Vue3 + Spring Boot技术栈，支持多角色权限管理，具备完整的用户认证和授权机制。

**🌟 如果这个项目对您有帮助，请给我一个 Star ⭐ 支持一下！**

## 🏗️ 项目架构

```
gym/
├── 前端/                          # 前端项目目录
│   └── gymnasium-project/         # Vue3前端应用
├── 后端/                          # 后端项目目录
│   └── gym-project-parent/        # Spring Boot后端应用
├── 示例/                          # 示例代码和参考项目
└── README.md                      # 项目说明文档
```

## 🛠️ 技术栈

### 前端技术栈
- **框架**: Vue 3.5.18 + TypeScript
- **构建工具**: Vite 5.2.11
- **UI组件库**: Element Plus 2.11.1
- **状态管理**: Pinia 3.0.3 (支持持久化)
- **路由管理**: Vue Router 4.5.1
- **HTTP客户端**: Axios 1.11.0
- **富文本编辑器**: WangEditor 5.1.23
- **图表库**: ECharts 6.0.0
- **样式预处理**: Sass 1.92.1
- **测试框架**: Vitest 3.2.4

### 后端技术栈
- **框架**: Spring Boot 2.4.4
- **Java版本**: JDK 1.8
- **数据库**: MySQL 8.0.33
- **ORM框架**: MyBatis Plus 3.4.1
- **连接池**: Druid 1.2.1
- **安全认证**: JWT (java-jwt 4.4.0)
- **API文档**: Swagger 2.9.2
- **文件存储**: MinIO 8.3.9
- **Excel处理**: EasyExcel 3.0.5
- **验证码**: Kaptcha 2.3.2
- **JSON处理**: FastJSON 1.2.83
- **工具库**: Apache Commons系列

## 📁 项目结构详解

### 前端项目结构
```
gymnasium-project/
├── src/
│   ├── api/                       # API接口定义
│   │   ├── course/               # 课程相关API
│   │   ├── login/                # 登录认证API
│   │   └── ...                   # 其他业务模块API
│   ├── components/               # 公共组件
│   ├── composables/              # 组合式函数
│   │   └── course/               # 课程相关组合函数
│   ├── hooks/                    # 自定义Hook
│   ├── http/                     # HTTP请求封装
│   ├── layout/                   # 布局组件
│   ├── router/                   # 路由配置
│   ├── store/                    # Pinia状态管理
│   ├── views/                    # 页面组件
│   │   ├── course/               # 课程管理页面
│   │   ├── member/               # 会员管理页面
│   │   ├── goods/                # 商品管理页面
│   │   └── ...                   # 其他业务页面
│   └── main.ts                   # 应用入口文件
├── package.json                  # 项目依赖配置
└── vite.config.ts               # Vite构建配置
```

### 后端项目结构
```
gym-project-parent/
├── gym-common/                   # 公共模块
│   └── src/main/java/com/noy/
│       ├── config/               # 配置类
│       ├── jwt/                  # JWT工具类
│       └── utils/                # 工具类
├── gym-service-web/              # Web服务模块
│   └── src/main/java/com/noy/
│       ├── config/               # 配置类
│       │   ├── MinioConfig.java  # MinIO配置
│       │   ├── SwaggerConfig.java # Swagger配置
│       │   └── WebMvcConfig.java # Web MVC配置
│       ├── controller/           # 文件上传控制器
│       └── web/                  # 业务模块
│           ├── course/           # 课程管理
│           ├── member/           # 会员管理
│           ├── goods/            # 商品管理
│           ├── equipment/        # 器材管理
│           ├── lost/             # 失物招领
│           ├── suggest/          # 意见建议
│           ├── login/            # 登录认证
│           ├── home/             # 首页统计
│           └── sys_*/            # 系统管理模块
└── pom.xml                       # Maven配置文件
```

## 🎯 核心功能模块

### 1. 用户认证与权限管理
- **登录系统**: 支持图形验证码的安全登录
- **JWT认证**: 基于Token的无状态认证机制
- **多角色支持**: 管理员、员工、会员三种角色
- **权限控制**: 基于菜单的细粒度权限管理
- **密码安全**: BCrypt加密存储用户密码

### 2. 会员管理系统
- **会员注册**: 完整的会员信息录入和管理
- **会员卡管理**: 支持多种会员卡类型和等级
- **充值记录**: 会员充值历史和余额管理
- **权限分配**: 为会员分配不同的系统权限

### 3. 课程管理系统
- **课程创建**: 支持富文本编辑的课程详情
- **图片上传**: 集成MinIO的课程封面图片管理
- **教练分配**: 课程与教练的关联管理
- **选课功能**: 会员在线选课和课程管理
- **课程统计**: 热门课程数据分析

### 4. 器材设备管理
- **器材登记**: 健身器材的详细信息管理
- **维护记录**: 器材维护和保养记录
- **状态跟踪**: 器材使用状态实时监控

### 5. 商品销售系统
- **商品管理**: 健身用品和营养品销售管理
- **订单处理**: 完整的下单和订单管理流程
- **销售统计**: 热销商品和销售数据分析

### 6. 失物招领
- **失物登记**: 失物信息的录入和管理
- **查询功能**: 便民的失物查询服务

### 7. 意见建议
- **反馈收集**: 会员意见和建议的收集管理
- **处理跟踪**: 意见建议的处理状态跟踪

### 8. 数据统计与分析
- **首页仪表板**: 关键业务数据的可视化展示
- **ECharts图表**: 丰富的数据图表展示
- **业务报表**: 各类业务数据的统计分析

## 🔧 核心技术实现

### 前端架构特点
1. **组合式API**: 使用Vue3 Composition API提高代码复用性
2. **TypeScript**: 全面的类型安全保障
3. **模块化设计**: 按业务模块组织代码结构
4. **响应式设计**: 适配不同屏幕尺寸的设备
5. **状态持久化**: Pinia配合本地存储实现状态持久化

### 后端架构特点
1. **分层架构**: Controller-Service-Mapper三层架构
2. **模块化设计**: 按业务领域划分模块
3. **统一响应**: 标准化的API响应格式
4. **异常处理**: 全局异常处理机制
5. **安全防护**: JWT + 权限拦截器的安全体系

### 文件存储方案
- **MinIO集成**: 分布式对象存储服务
- **图片上传**: 支持多种图片格式的上传和预览
- **文件管理**: 自动创建存储桶和文件组织

### 数据库设计
- **MySQL**: 关系型数据库存储业务数据
- **MyBatis Plus**: 简化数据库操作和查询
- **连接池**: Druid连接池优化数据库性能

## 🚀 快速开始

### 环境要求
- Node.js 20.19.0+ 或 22.12.0+
- JDK 1.8+
- MySQL 8.0+
- Maven 3.6+
- MinIO Server

### 前端启动
```bash
cd 前端/gymnasium-project
npm install
npm run dev
```
访问地址: http://localhost:5173

### 后端启动
```bash
cd 后端/gym-project-parent
mvn spring-boot:run -pl gym-service-web
```
API地址: http://localhost:8089

### MinIO启动
```bash
# 使用Docker启动MinIO
docker run -p 9000:9000 -p 9001:9001 \
  --name minio \
  -v /mnt/data:/data \
  -e "MINIO_ROOT_USER=minioadmin" \
  -e "MINIO_ROOT_PASSWORD=minioadmin" \
  quay.io/minio/minio server /data --console-address ":9001"
```
控制台地址: http://localhost:9001

### 数据库配置
1. 创建数据库 `gymnasium`
2. 修改 `application-dev.yml` 中的数据库连接信息
3. 运行项目自动创建表结构

## 📊 系统截图

### 登录界面
- 图形验证码验证
- 用户名密码登录
- 记住登录状态

### 管理后台
- 响应式侧边栏导航
- 权限控制的菜单显示
- 现代化的UI设计

### 业务功能
- 课程管理界面
- 会员信息管理
- 数据统计图表
- 文件上传功能

## 🔐 安全特性

1. **身份认证**: JWT Token认证机制
2. **权限控制**: 基于角色的访问控制(RBAC)
3. **密码安全**: BCrypt加密存储
4. **验证码**: 图形验证码防止暴力破解
5. **CORS配置**: 跨域请求安全控制
6. **SQL注入防护**: MyBatis Plus参数化查询

## 📈 性能优化

1. **前端优化**:
   - Vite构建工具快速开发和构建
   - 组件懒加载减少初始加载时间
   - Pinia状态管理优化数据流

2. **后端优化**:
   - Druid连接池优化数据库连接
   - MyBatis Plus提高ORM性能
   - 分页查询减少数据传输量

3. **存储优化**:
   - MinIO分布式存储提高文件访问速度
   - 图片压缩减少存储空间

## 🤝 贡献指南

1. Fork 项目
2. 创建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

## 📝 开发规范

### 前端规范
- 使用 TypeScript 进行类型检查
- 遵循 Vue 3 Composition API 最佳实践
- 组件命名采用 PascalCase
- 文件命名采用 kebab-case

### 后端规范
- 遵循 RESTful API 设计原则
- 使用统一的响应格式
- 异常处理要完整和规范
- 代码注释要清晰明了

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情

## 📞 联系方式

如有问题或建议，请通过以下方式联系：
- 项目Issues: [GitHub Issues](https://github.com/noii-y/issues)
- 邮箱: 2803038543@qq.com

---

**注意**: 这是一个学习和演示项目，如需用于生产环境，请确保进行充分的安全测试和性能优化。
