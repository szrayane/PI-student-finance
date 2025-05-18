import React, { useState } from 'react'
import { PieChart, Pie, Cell, Tooltip, Legend } from 'recharts'
import './Despesas.css'

const initialExpenses = [
  { name: 'Aluguel', value: 1200 },
  { name: 'Transporte', value: 300 },
  { name: 'Alimentação', value: 600 },
  { name: 'Lazer', value: 250 },
  { name: 'Educação', value: 400 },
]

const COLORS = ['#8884d8', '#82ca9d', '#ffc658', '#ff7f50', '#a28bd4', '#61dafb', '#ffb3b3']

const ExpenseDashboard = () => {
  const [expenses, setExpenses] = useState(initialExpenses)
  const [newName, setNewName] = useState('')
  const [newValue, setNewValue] = useState('')

  const handleAddExpense = (e) => {
    e.preventDefault()
    if (!newName.trim() || isNaN(newValue) || Number(newValue) <= 0) {
      alert('Preencha os campos corretamente.')
      return
    }

    setExpenses([...expenses, { name: newName.trim(), value: parseFloat(newValue) }])
    setNewName('')
    setNewValue('')
  }

  return (
    <div className="dashboard-container">
      <h1 className="dashboard-title">Resumo de Despesas</h1>

      {/* Formulário de Nova Despesa */}
      <form onSubmit={handleAddExpense} className="expense-form">
        <input
          type="text"
          placeholder="Nome da despesa"
          value={newName}
          onChange={(e) => setNewName(e.target.value)}
          className="form-input"
        />
        <input
          type="number"
          placeholder="Valor (R$)"
          value={newValue}
          onChange={(e) => setNewValue(e.target.value)}
          className="form-input"
        />
        <button type="submit" className="form-button">
          Adicionar
        </button>
      </form>

      <div className="dashboard-content">
        {/* Lista de Despesas */}
        <div className="expense-list">
          {expenses.map((expense, index) => (
            <div className="expense-card" key={index}>
              <div>
                <span className="expense-name">{expense.name}</span>
                <span className="expense-value">R$ {expense.value.toFixed(2)}</span>
              </div>
              <button
                className="remove-button"
                onClick={() => {
                  const confirmDelete = window.confirm(`Remover "${expense.name}"?`)
                  if (confirmDelete) {
                    setExpenses(expenses.filter((_, i) => i !== index))
                  }
                }}>
                🗑️
              </button>
            </div>
          ))}
        </div>

        {/* Gráfico de Pizza */}
        <div className="chart-container">
          <PieChart width={400} height={400}>
            <Pie
              data={expenses}
              cx="50%"
              cy="50%"
              outerRadius={130}
              fill="#8884d8"
              dataKey="value"
              nameKey="name"
              label={({ name, percent }) => `${name}: ${(percent * 100).toFixed(0)}%`}>
              {expenses.map((_, index) => (
                <Cell key={index} fill={COLORS[index % COLORS.length]} />
              ))}
            </Pie>
            <Tooltip />
            <Legend />
          </PieChart>
        </div>
      </div>
    </div>
  )
}

export default ExpenseDashboard
