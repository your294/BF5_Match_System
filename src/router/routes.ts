import ACCESS_ENUM from "@/access/accessEnum"
import type { RouteRecordRaw } from "vue-router"

const testView = () => import("../views/TestView.vue")
const searchView = () => import("../views/SearchView.vue")
const teamView = () => import("../views/TeamView.vue")
const userSettingView = () => import("../views/user/UserSettingView.vue")
const userEditView = () => import("../views/user/UserEditView.vue")
const userLayout = () => import("../layouts/UserLayout.vue")
const userLoginView = () => import ("../views/user/UserLoginView.vue")
const userRegisterView = () => import("../views/user/UserRegistserView.vue")
const noAuthView = () => import("../views/NotFoundView.vue")

export const routes: Array<RouteRecordRaw> = [
    {
        path: "/user",
        name: "用户界面",
        component: userLayout,
        children: [
            {
                path: "/user/login",
                name: "用户登录",
                component: userLoginView,
            },
            {
                path: "/user/register",
                name: "用户注册",
                component: userRegisterView
            },
            {
                path: "/user/setting",
                name: "用户设置",
                component: userSettingView,
                meta: {
                    access: ACCESS_ENUM.USER
                }
            },
            {
                path: "/user/edit",
                name: "用户编辑",
                component: userEditView,
                meta: {
                    access: ACCESS_ENUM.USER
                }
            }
        ]
    },
    {
        path: "/",
        name: "主页",
        component: testView
    },
    {
        path: "/search",
        name: "搜索",
        component: searchView
    },
    {
        path: "/Team",
        name: "队伍",
        component: teamView
    },
    {
        path: "/noAuth",
        name: "noAuth",
        component: noAuthView
    }
]