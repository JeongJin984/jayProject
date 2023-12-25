'use client'

import axios from "axios"

export default function Home() {

  const onclickLogin = () => {
    axios({
      method: "GET",
      url: 'http://localhost:8080/api/v1/token/get'
    }).then(res => {
      axios.defaults.headers.common['Authorization'] = 'Bearer ' + res.data
    })
  }

  return (
    <div>
      <div>
        <input placeholder="로그인"/>
        <button onClick={onclickLogin}>로그인</button>
      </div>
      <div>
        <input placeholder='상품ID'/>
        <button>구매</button>
      </div>
      <div>
        <input placeholder='상품등록'/>
        <button>등록</button>
      </div>
    </div>
  )
}
