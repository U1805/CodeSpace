import axios from 'axios'
import { Algo, Result, User } from './interface'

const axios_ins = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 60000
})

export const register = async (form: {
    username: string
    password: string
    admin: boolean
}) => {
    const data = {
        username: form.username,
        password: form.password
    }
    console.log(data);
    
    const result:Result = (await axios_ins.post("/register", data)).data
    if (result.code == 1) {
        localStorage.setItem('userToken', form.username)
    } else {
        alert(result.msg)
    }
}

export const login = async (form: {
    username: string
    password: string
    admin: boolean
}) => {
    const result:Result = (await axios_ins.post("/login", form)).data
    if (result.code == 1) {
        localStorage.setItem('userToken', form.username)
    } else {
        alert(result.msg)
    }
}

export const getUser = async (username: string) => {
    const params = {
        username: username
    }
    const result: Result = (await axios_ins.get("/users", { params })).data
    console.log(result);
    
    if (result.code == 1) {
        const user: User = result.data
        console.log(user);
        
        return user
    } else {
        alert(result.msg)
    }
}

export const uploadAlgo = async (form:{
    title: string,
    content: string,
    author: string,
    line: number,
    language: string,
    desc: string,
    tags: string[]
}) => {
    console.log(form);
    
    const result:Result = (await axios_ins.post("/algos", form)).data
    // console.log(data)
    // const result: Result = {
    //     code: 1,
    //     msg: 'success',
    //     data: null
    // }
    if (result.code == 1) {
        return
    } else {
        alert(result.msg)
    }
}

export const search = async (keyword: string, username: string) => {
    const params = {
        keyword: keyword,
        author: username
    }
    const result:Result = (await axios_ins.get('/algos', { params })).data
    if (result.code == 1) {
        return result.data
    } else {
        alert(result.msg)
    }
}
