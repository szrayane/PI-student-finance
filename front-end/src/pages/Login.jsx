import { useState } from 'react'
import './Login.css'

export default function Login() {
  const [form, setForm] = useState({ email: '', password: '' })

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value })
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    // Aqui você faria a autenticação
    console.log('Login com:', form)
  }

  return (
    <form className="login-form" onSubmit={handleSubmit}>
      <h2>Login</h2>
      <input type="email" name="email" placeholder="Email" value={form.email} onChange={handleChange} required />
      <input
        type="password"
        name="password"
        placeholder="Senha"
        value={form.password}
        onChange={handleChange}
        required
      />
      <button type="submit">Entrar</button>
    </form>
  )
}
