import { useState } from 'react'
import './Cadastro.css'

export default function Register() {
  const [form, setForm] = useState({ name: '', email: '', password: '' })

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value })
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    createUser(form)
  }

  return (
    <>
      <h1 className="appHeader">Cadastro de Usuário</h1>
      <form onSubmit={handleSubmit} className="register-form">
        <input className="input" name="name" placeholder="Nome" onChange={handleChange} />
        <input className="input" name="email" placeholder="Email" onChange={handleChange} />
        <input className="input" name="password" type="password" placeholder="Senha" onChange={handleChange} />
        <button className="cadastro" type="submit">
          Cadastrar
        </button>
      </form>
    </>
  )
}
