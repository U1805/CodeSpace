import axios from 'axios'
import { useRouter } from 'vue-router'
import { Algo, Result, User } from './interface'

const axios_ins = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 1000
})

export const register = (username: string, password: string) => {
    const data = {
        username: username,
        password: password
    }
    // const result:Result = await axios_ins.post("/register", data)
    const result: Result = {
        code: 1,
        msg: 'success',
        data: 'cs-afsfrqwe'
    }
    const router = useRouter()
    if (result.code == 1) {
        localStorage.setItem('userToken', username)
        router.push({ name: 'info' })
    } else {
        alert(result.msg)
    }
}

export const login = async (username: string, password: string, admin: boolean) => {
    const data = {
        username: username,
        password: password,
        admin: admin
    }
    // const result:Result = await axios_ins.post("/login", data)
    const result: Result = {
        code: 1,
        msg: 'success',
        data: 'cs-afsfrqwe'
    }
    const router = useRouter()
    if (result.code == 1) {
        localStorage.setItem('userToken', username)
        router.push({ name: 'info' })
    } else {
        alert(result.msg)
    }
}

export const getUser = async (username: string) => {
    const params = {
        username: username
    }
    // const result: Result = await axios_ins.get("/user", { params })
    const result: Result = {
        code: 1,
        msg: 'success',
        data: {
            username: 'Kaho',
            avatar: 'https://avatars.githubusercontent.com/u/171446778',
            email: '123@email.com',
            repo: 20,
            score: 120,
            line: 2000
        }
    }
    if (result.code == 1) {
        const user: User = result.data
        return user
    } else {
        alert(result.msg)
    }
}

export const uploadAlgo = (
    title: string,
    content: string,
    author: string,
    line: number,
    language: string,
    desc: string,
    tags: string[]
) => {
    const data: Algo = {
        title: title,
        content: content,
        author: author,
        line: line,
        language: language,
        desc: desc,
        tags: tags
    }
    // const result:Result = await axios_ins.post("/algo", data)
    console.log(data)
    const result: Result = {
        code: 1,
        msg: 'success',
        data: null
    }
    if (result.code == 1) {
        return
    } else {
        alert(result.msg)
    }
}

export const search = async (keyword: string, username: string) => {
    const params = {
        keyword: keyword,
        username: username
    }
    // const result:Result = await axios_ins.get('/algo', { params })
    const result: Result = {
        code: 1,
        msg: 'success',
        data: [
            {
                author: 'Alice',
                title: 'code1',
                language: 'javascript',
                desc: '',
                tags: ['javascript', 'bb', 'cc'],
                content:
                    'const tagOptions = [ \n{ \n    value: "HTML", \n    label: "HTML" }, \n{ \n    value: "CSS", \n    label: "CSS" }, \n{ \n    value: "JavaScript", \n    label: "JavaScript" \n} \n]',
                line: 3
            },
            {
                author: 'Bob',
                title: 'code2',
                language: 'python',
                desc: 'Tom',
                tags: ['python', 'bb', 'cc'],
                content: 'print("hello world")',
                line: 3
            },
            {
                author: 'Clash',
                title: 'code3',
                language: 'c',
                desc: '6-05-0',
                tags: ['c', 'bb', 'cc'],
                content: 'No. 189, Grove St, Los Angeles',
                line: 3
            },
            {
                author: '2016-05-01',
                title: 'Tom',
                language: 'cpp',
                desc: '',
                tags: ['cpp', 'bb', 'cc'],
                content: 'No. 189, Grove St, Los Angeles',
                line: 3
            }
        ]
    }
    if (result.code == 1) {
        return result.data
    } else {
        alert(result.msg)
    }
}
