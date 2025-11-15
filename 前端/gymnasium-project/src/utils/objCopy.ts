// 对象复制：仅复制目标已有键的值

// 仅复制目标已有属性，避免新增键
export default function objCopy<S extends Record<string, any>, T extends Record<string, any>>(source: S, target: T): void {
  if (!source || !target) return
  Object.keys(target).forEach((key) => {
    if (
      Object.prototype.hasOwnProperty.call(source, key) &&
      source[key] !== undefined &&
      source[key] !== null
    ) {
      ;(target as any)[key] = source[key]
    }
  })
}