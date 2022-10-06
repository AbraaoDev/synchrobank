import {BrowserRouter } from 'react-router-dom'
import { ToastContainer } from 'react-toastify'
import { ThemeProvider } from 'styled-components'
import {Router} from './Router'
import { GlobalStyles } from './styles/global'
import { defaultTheme } from './styles/theme/defaultTheme'
import  'react-toastify/dist/ReactToastify.min.css'

function App() {

  return (
    <BrowserRouter>
      <ThemeProvider theme={defaultTheme}>
        <GlobalStyles />
        <Router />
        <ToastContainer />
      </ThemeProvider>
        
 
    </BrowserRouter>
  )
}

export default App
