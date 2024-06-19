import axios from 'axios'
import { Algo, Result, User } from './interface'

const axios_ins = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 60000
})

export const register = async (form: { username: string; password: string; admin: boolean }) => {
    const data = {
        username: form.username,
        password: form.password
    }
    console.log(data)

    const result: Result = (await axios_ins.post('/register', data)).data
    if (result.code == 1) {
        localStorage.setItem('userToken', result.data)
        localStorage.setItem('username', form.username)
        window.location.href = '/'
        return result.msg
    } else {
        return result.msg
    }
}

export const login = async (form: { username: string; password: string; admin: boolean }) => {
    const result: Result = (await axios_ins.post('/login', form)).data
    if (result.code == 1) {
        localStorage.setItem('userToken', result.data)
        localStorage.setItem('username', form.username)
        window.location.href = '/'
        return result.msg
    } else {
        return result.msg
    }
}

export const getUser = async (username: string) => {
    const result: Result = (await axios_ins.get(`/users/${username}`)).data
    console.log(result)

    if (result.code == 1) {
        const user: User = result.data
        console.log(user)
        return user
    } else {
        window.location.href = '/login'
    }
}

export const getAllUser = async () => {
    const result: Result = (await axios_ins.get('/users/all')).data
    console.log(result)

    return result.data as User[]
}

export const deleteUser = async (username: string) => {
    const result: Result = (await axios_ins.delete(`/users/${username}`)).data

    console.log(result)
    if (result.code == 1) {
        return result.msg
    } else {
        return result.msg
    }
}

export const uploadAlgo = async (form: {
    title: string
    content: string
    author: string
    line: number
    language: string
    desc: string
    tags: string[]
}) => {
    console.log(form)

    const result: Result = (await axios_ins.post('/algos', form)).data
    if (result.code == 1) {
        return result.data
    } else {
        return -1
    }
}

export const search = async (keyword: string, username: string) => {
    const params = {
        keyword: keyword,
        author: username
    }
    const result: Result = (await axios_ins.get('/algos', { params })).data
    if (result.code == 1) {
        return result.data
    } else {
        alert(result.msg)
    }
}

export const editInfo = async (form: { username: string; avatar: string; email: string }) => {
    console.log(form)
    const result: Result = (await axios_ins.put('/users', form)).data
    return result.msg
}
