<template>
    <el-table :data="tableData" style="width: 100%; height: 100%">
        <el-table-column prop="username" label="用户名" width="180" />
        <el-table-column prop="repo" label="算法库" width="120" />
        <el-table-column prop="score" label="积分" width="120" />
        <el-table-column prop="line" label="行数" width="120" />
        <el-table-column fixed="right" label="操作" width="120">
            <template #default="scope">
                <el-button link type="primary" size="small" @click="setUserImpl(scope.$index)"
                    >Detail</el-button
                >
                <el-button link type="danger" size="small" @click="deleteUserImpl(scope.$index)">Delete</el-button>
            </template>
        </el-table-column>
    </el-table>
</template>

<script lang="ts" setup>
import { User } from '@/assets/interface'
import { deleteUser, getAllUser } from '@/assets/request'
import { inject, onMounted, ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'

const setUser = inject('setUser') as Function
const setUserImpl = (index: number) => {
    setUser(tableData.value[index].username)
}

const deleteUserImpl = async (index: number) => {    
    if (tableData.value[index].username == 'admin') {
        ElMessage({
            type: 'error',
            message: '管理员用户不可删除',
        })
        return
    }
    ElMessageBox.confirm('删除用户的操作不可逆，是否继续？', 'Warning', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
    })
        .then(async () => {
            const res = await deleteUser(tableData.value[index].username)
            if (res === 'success') {
                ElMessage({
                    type: 'success',
                    message: '删除成功'
                })
            }else{
                ElMessage({
                    type: 'error',
                    message: res,
                })
            }
            tableData.value = await getAllUser()
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '取消删除'
            })
        })
}

const tableData = ref<User[]>([])

onMounted(async () => {
    tableData.value = await getAllUser()
})
</script>
