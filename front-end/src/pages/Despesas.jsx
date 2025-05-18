import './Despesas.css'
import React from 'react'
import { PieChart, Pie, Cell, Tooltip, Legend } from 'recharts'

// Dados fictícios de despesas
const expenses = [
  { name: 'Aluguel', value: 1200 },
  { name: 'Transporte', value: 300 },
  { name: 'Alimentação', value: 600 },
  { name: 'Lazer', value: 250 },
  { name: 'Educação', value: 400 },
]

const COLORS = ['#8884d8', '#82ca9d', '#ffc658', '#ff7f50', '#a28bd4']

const ExpenseDashboard = () => {
  return (
    <div className="dashboard-container">
      <h1 className="dashboard-title">Resumo de Despesas</h1>
      <div className="dashboard-content">
        {/* Lista de Despesas */}
        <div className="expense-list">
          {expenses.map((expense, index) => (
            <div className="expense-card" key={index}>
              <span className="expense-name">{expense.name}</span>
              <span className="expense-value">R$ {expense.value.toFixed(2)}</span>
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
