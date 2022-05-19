import { BrowserRouter, Route, Routes } from "react-router-dom";
import Main from "./routes/Main";
import World from "./routes/World";
import "./App.css";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        {/* <Header /> */}
        <Routes>
          <Route path="/" element={<Main />} />
          <Route path="/world/*" element={<World />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
