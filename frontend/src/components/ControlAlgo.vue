<template>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column label="算法名" width="140">
        <template #default="scope">
          <div style="display: flex; align-items: center">
            <el-icon><timer /></el-icon>
            <span style="margin-left: 10px">{{ scope.row.title }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="描述" width="120">
        <template #default="scope">
            {{ scope.row.desc.substring(0,6) }}
        </template>
      </el-table-column>
      <el-table-column label="作者" width="100">
        <template #default="scope">
            {{ scope.row.author }}
        </template>
      </el-table-column>
      <el-table-column label="行数" width="80">
        <template #default="scope">
            {{ scope.row.line }}
        </template>
      </el-table-column>
      <el-table-column label="实现语言" width="80">
        <template #default="scope">
            {{ scope.row.language }}
        </template>
      </el-table-column>
      <el-table-column label="标签" >
        <template #default="scope">
            <el-tag v-for="(tag, index) in scope.row.tags.slice(0,3)" style="margin-right: 10px">{{ tag }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="180">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)">
            Edit
          </el-button>
          <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
          >
            Delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </template>

<script setup lang="ts">
import { Algo } from '@/assets/interface';
import { getUser, search } from '@/assets/request';
import { Timer } from '@element-plus/icons-vue'
import { reactive, ref, watch } from 'vue';

const props = defineProps(["username"])

const handleEdit = (index: number, row: Algo) => {
  console.log(index, row)
}
const handleDelete = (index: number, row: Algo) => {
  console.log(index, row)
}

const tableData = ref<Algo[]>([])


watch(props, async (newValue, oldValue) => {
    console.log(newValue.username);
    tableData.value = await search("", newValue.username)
})
</script>