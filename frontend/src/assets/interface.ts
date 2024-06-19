export interface Result{
    code: number
    msg: string
    data: any
}

export interface User{
    username: string
    avatar: string
	email: string
	repo: number
	score: number
	line: number
}

export interface Algo{
    id: number
    title: string,
    content: string,
    author: string,
    line: number,
    language: string,
    desc: string,
    origin: boolean,
    tags: string[],
    createTime: string,
    updateTime: string
}