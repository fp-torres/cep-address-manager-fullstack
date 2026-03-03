import React, { useState, useEffect } from "react";
import axios from "axios";
import "./App.css";

const API_URL = "http://localhost:8080/usuarios";

function App() {
  const [form, setForm] = useState({
    nome: "",
    cpf: "",
    cep: ""
  });

  const [usuarios, setUsuarios] = useState([]);
  const [erro, setErro] = useState(null);
  const [sucesso, setSucesso] = useState(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    listarUsuarios();
  }, []);

  const listarUsuarios = async () => {
    try {
      const response = await axios.get(API_URL);
      setUsuarios(response.data);
    } catch {
      setErro("Erro ao carregar usuários.");
    }
  };

  // ===== MÁSCARA CPF =====
  const formatCpf = (value) => {
    const numbers = value.replace(/\D/g, "").slice(0, 11);
    return numbers
      .replace(/(\d{3})(\d)/, "$1.$2")
      .replace(/(\d{3})(\d)/, "$1.$2")
      .replace(/(\d{3})(\d{1,2})$/, "$1-$2");
  };

  // ===== MÁSCARA CEP =====
  const formatCep = (value) => {
    const numbers = value.replace(/\D/g, "").slice(0, 8);
    return numbers.replace(/(\d{5})(\d)/, "$1-$2");
  };

  const handleChange = (e) => {
    let { name, value } = e.target;

    if (name === "cpf") value = formatCpf(value);
    if (name === "cep") value = formatCep(value);

    setForm({ ...form, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setErro(null);
    setSucesso(null);
    setLoading(true);

    try {
      await axios.post(API_URL, {
        nome: form.nome.trim(),
        cpf: form.cpf.replace(/\D/g, ""),
        cep: form.cep.replace(/\D/g, "")
      });

      setSucesso("Usuário cadastrado com sucesso!");
      setForm({ nome: "", cpf: "", cep: "" });
      listarUsuarios();
    } catch (err) {
      if (err.response && err.response.data) {
        const mensagens = Object.values(err.response.data).join(" | ");
        setErro(mensagens);
      } else {
        setErro("Erro ao conectar com o servidor.");
      }
    } finally {
      setLoading(false);
    }
  };

  const deletar = async (id) => {
    if (!window.confirm("Deseja realmente excluir este usuário?")) return;
    await axios.delete(`${API_URL}/${id}`);
    listarUsuarios();
  };

  return (
    <div className="app">
      <div className="card">
        <h1>CEP Address Manager</h1>

        <form onSubmit={handleSubmit}>
          <input
            type="text"
            name="nome"
            placeholder="Nome completo"
            value={form.nome}
            onChange={handleChange}
            maxLength={100}
            required
          />

          <input
            type="text"
            name="cpf"
            placeholder="CPF"
            value={form.cpf}
            onChange={handleChange}
            required
          />

          <input
            type="text"
            name="cep"
            placeholder="CEP"
            value={form.cep}
            onChange={handleChange}
            required
          />

          <button type="submit" disabled={loading}>
            {loading ? "Salvando..." : "Salvar"}
          </button>
        </form>

        {erro && <div className="error">{erro}</div>}
        {sucesso && <div className="success">{sucesso}</div>}
      </div>

      <div className="list">
        <h2>Usuários Salvos</h2>

        {usuarios.length === 0 && <p>Nenhum usuário cadastrado.</p>}

        {usuarios.map((u) => (
          <div key={u.id} className="user-card">
            <div className="user-header">
              <h3>{u.nome}</h3>
              <button className="delete" onClick={() => deletar(u.id)}>
                Excluir
              </button>
            </div>

            <p><strong>CPF:</strong> {formatCpf(u.cpf)}</p>
            <p>
              <strong>Endereço:</strong> {u.logradouro}, {u.bairro}<br />
              {u.cidade} - {u.estado}
            </p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;