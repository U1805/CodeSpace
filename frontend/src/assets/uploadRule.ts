import { FormRules } from 'element-plus'
import { reactive } from 'vue'

export interface RuleForm {
    title: string
    content: string
    author: string
    tags: string[]
    desc: string
    line: number
    language: string
}

export const rules = reactive<FormRules<RuleForm>>({
    title: [
        { required: true, message: 'Please input title', trigger: 'blur' },
        { max: 20, message: 'Length should be shorter than 20', trigger: 'blur' }
    ],
    content: [
        {
            required: true,
            message: 'Please select a file',
            trigger: 'change'
        }
    ],
    author: [
        {
            required: true,
            trigger: 'change'
        }
    ]
})
