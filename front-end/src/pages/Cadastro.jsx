import { useState } from 'react'
import './Cadastro.css'
import { Link } from 'react-router-dom'

export default function Register() {
  const [form, setForm] = useState({ nome: '', email: '', senha: '' })

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value })
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    createUser(form)
  }

  const createUser = async (user) => {
    try {
      const response = await fetch('http://localhost:8090/usuarios', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(user),
      })

      if (response.ok) {
        alert('Usuário cadastrado com sucesso!')
      } else {
        alert('Erro ao cadastrar usuário.')
        console.error('Status:', response.status)
      }
    } catch (error) {
      console.error('Erro de conexão:', error)
      alert('Erro na comunicação com o servidor.')
    }
  }

  return (
    <>
      <h1 className="appHeader">Cadastro de Usuário</h1>
      <form onSubmit={handleSubmit} className="register-form">
        <input className="input" name="nome" placeholder="Nome" onChange={handleChange} />
        <input className="input" name="email" placeholder="Email" onChange={handleChange} />
        <input className="input" name="senha" type="password" placeholder="Senha" onChange={handleChange} />
        <button className="cadastro" type="submit">
          Cadastrar
        </button>
        <p className="register-link">
          Ja possue uma conta? <Link to="/login">Faça login</Link>
        </p>
      </form>
    </>
  )
}
