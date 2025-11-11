/**
 * 对象属性复制工具类
 * 提供对象间属性快速复制的功能
 */

/**
 * 对象属性复制函数
 * 将源对象(obj1)的属性值复制到目标对象(obj2)中
 * 只复制目标对象中已存在的属性，不会添加新属性
 * 
 * @param obj1 源对象 - 提供属性值的对象
 * @param obj2 目标对象 - 接收属性值的对象
 * @returns void
 * 
 * @example
 * ```typescript
 * // 使用示例
 * const source = { name: '张三', age: 25, city: '北京' }
 * const target = { name: '', age: 0 }
 * 
 * objCoppy(source, target)
 * // 结果: target = { name: '张三', age: 25 }
 * // 注意: city属性不会被复制，因为target中没有这个属性
 * ```
 */
export default function objCoppy(obj1: any, obj2: any): void {
  // 参数验证：确保两个参数都存在
  if (!obj1 || !obj2) return;

  // 遍历目标对象的所有属性，仅在源对象存在且值不为undefined时复制，避免覆盖默认值
  Object.keys(obj2).forEach((key) => {
    if (
      Object.prototype.hasOwnProperty.call(obj1, key) &&
      obj1[key] !== undefined &&
      obj1[key] !== null
    ) {
      obj2[key] = obj1[key];
    }
  });
}