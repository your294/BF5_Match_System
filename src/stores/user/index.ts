import ACCESS_ENUM from "@/access/accessEnum";
import { UserControllerService } from "../../../generated";
import { defineStore } from "pinia";

export const userInfoStore = defineStore('user', {
    state: () => ({
        loginUser: {
            id: "-1",
            userName: "未登录",
            userRole: ACCESS_ENUM.NOT_LOGIN,
            userAvatar: "",
            userProfile: "空",
            tags: [],
        }
    }),
    actions: {
        updateLoginInfo(data: any) {
            this.loginUser = data;
            if (data.userName === null || data.userName === undefined 
                || data.userName === "") {
                    data.userName = "用户名为空";
                }
        },
        async getLoginUser() {
            const res = await UserControllerService.getLoginUserUsingGet();
            if (res.code === 0) {
                this.updateLoginInfo(res.data);
            }else {
                this.updateLoginInfo({
                    ...this.loginUser,
                    UserRole: ACCESS_ENUM.NOT_LOGIN
                })
            }
        }
    }
})