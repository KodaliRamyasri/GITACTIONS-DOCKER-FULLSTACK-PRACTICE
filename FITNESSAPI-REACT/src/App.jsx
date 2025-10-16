import { useState } from 'react'
import FitnessUserManager from './components/FitnessUserManager';

function App() {
  const [count, setCount] = useState(0)

  return (
      <div>
        <FitnessUserManager/>
      </div>  
  )
}

export default App
