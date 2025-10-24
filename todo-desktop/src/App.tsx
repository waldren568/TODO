import React from 'react';
import './App.css';

function App() {
  return (
    <div className="App">
      <iframe
        src="http://localhost:8080"  // URL della tua app Spring Boot
        style={{ width: '100%', height: '100vh', border: 'none' }}
        title="TODO App"
      />
    </div>
  );
}

export default App;