<template>
    <div id="UserEditView">
        <van-form @submit="updateUserInfo">
            <van-cell-group inset>
                <van-field
                v-model="editvalue"
                :name="editvalue"
                :label="props.EditValue"
                placeholder="input new value"
                :rules="[{ required: true, message: 'value is required' }]"
                />
            </van-cell-group>
            <div style="margin: 16px;">
                <van-button round block type="primary" native-type="submit">
                提交
                </van-button>
            </div>
        </van-form>

    </div>
</template>

<script setup lang="ts">
import router from '@/router';
import { UserControllerService } from '../../../generated';
import { userInfoStore } from '../../stores/user'
import { ref } from 'vue'
import type { Ref } from 'vue'
import { toValue } from 'vue';

const store = userInfoStore();

interface UserInfo {
    id: string;
    userName: string;
    userRole: string;
    userAvatar: string;
    userProfile: string;
    tags: any[];
    email: string;
    telephone: string;
}

interface Props {
    EditValue: keyof UserInfo;
}


const props = withDefaults(defineProps<Props>(), {
    EditValue: "userName"
})

const editvalue: Ref<string> = ref(props.EditValue);

const updateUserInfo = async () => {
    const updateUser = {} as Partial<UserInfo>;

    switch (props.EditValue) {
        case "id":
            updateUser.id = editvalue.value;
            break;
        case "userName":
            updateUser.userName = editvalue.value;
            break;
        case "userAvatar":
            updateUser.userAvatar = editvalue.value;
            break;
        case "email":
            updateUser.email = editvalue.value;
            break;
        case "telephone":
            updateUser.telephone = editvalue.value;
            break;
        case "userProfile":
            updateUser.userProfile = editvalue.value;
            break;
        case "tags":
            updateUser.tags = editvalue.value.split(",");
            break;
        // 其他属性的更新类似
        default:
            break;
    }
    const res = await UserControllerService.updateMyUserUsingPost({
        ...updateUser
    });
    if (res.code === 0) {
        alert("用户信息更新成功");
        store.updateLoginInfo(updateUser);
    }else {
        alert("用户信息更新失败");
    }
    router.push("/user/setting");
}
</script>