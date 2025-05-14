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
    <form onSubmit={handleSubmit} className="register-form">
      <input name="name" placeholder="Nome" onChange={handleChange} />
      <input name="email" placeholder="Email" onChange={handleChange} />
      <input name="password" type="password" placeholder="Senha" onChange={handleChange} />
      <button type="submit">Cadastrar</button>
    </form>
  )
}
