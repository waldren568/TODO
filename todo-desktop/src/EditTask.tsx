import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';

interface Task {
  id?: number;
  title: string;
  description?: string;
  completed: boolean;
}

function EditTask() {
  const [task, setTask] = useState<Task>({ title: '', description: '', completed: false });
  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    const fetchTask = async () => {
      try {
        const res = await axios.get(`http://localhost:8080/api/task/${id}`);
        setTask(res.data);
      } catch (error) {
        console.error('Errore nel caricamento del task:', error);
      }
    };

    if (id) {
      fetchTask();
    }
  }, [id]);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await axios.put(`http://localhost:8080/api/task/${id}`, task);
      navigate('/');
    } catch (error) {
      console.error('Errore nell\'aggiornamento del task:', error);
    }
  };

  return (
    <div>
      <h1>Edit Task</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Title:</label>
          <input
            type="text"
            value={task.title}
            onChange={(e) => setTask({...task, title: e.target.value})}
            required
          />
        </div>
        <div>
          <label>Description:</label>
          <textarea
            value={task.description || ''}
            onChange={(e) => setTask({...task, description: e.target.value})}
          />
        </div>
        <button type="submit">Update Task</button>
        <button type="button" onClick={() => navigate('/')}>Cancel</button>
      </form>
    </div>
  );
}

export default EditTask;