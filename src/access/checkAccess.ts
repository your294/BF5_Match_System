import ACCESS_ENUM from './accessEnum'

/**
 * 权限管理函数
 * @param loginUser 登录用户信息对象 
 * @param needLogin 需要查验的登录权限
 * @returns boolean
 */
export const checkAccess = (loginUser: any, needLogin = ACCESS_ENUM.NOT_LOGIN) => {
    // 获取当前用户拥有的权限，没有视作未登录
    const loginUserAccess = loginUser?.userRole ?? ACCESS_ENUM.NOT_LOGIN;
    // 如果权限不需要登录返回true
    if (needLogin === ACCESS_ENUM.NOT_LOGIN) {
        return true;
    }
    // 如果权限需要用户权限
    if (needLogin === ACCESS_ENUM.USER) {
        // user and admin all go in
        if (loginUserAccess === ACCESS_ENUM.NOT_LOGIN) {
            return false;
        }
    }
    // 权限是root
    if (needLogin === ACCESS_ENUM.ADMIN) {
        if (loginUserAccess !== ACCESS_ENUM.ADMIN) {
            return false;
        }
    }
    return true;
}

