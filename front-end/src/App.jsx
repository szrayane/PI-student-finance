import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom'
import Register from './pages/Cadastro'
import Login from './pages/Login'
import './App.css'

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/cadastro" element={<Register />} />
        <Route path="/login" element={<Login />} />
      </Routes>
    </Router>
  )
}

export default App
