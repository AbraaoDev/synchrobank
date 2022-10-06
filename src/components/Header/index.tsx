import { NavLink } from 'react-router-dom'
import logo from '../../assets/logo.svg'
import { ButtonAccount } from '../ButtonAccount'
import { HeaderContainer, HeaderContent } from './styles'

export function Header(){

    return(
        <HeaderContainer> 
            <HeaderContent>
                <a href="/">
                    <img src={logo} alt="SynchroBank" />
                </a>
                
                <nav>  
                    <NavLink to={"/home"}>Home</NavLink>
                    <NavLink to={"/accounts"}>Accounts</NavLink>
                    <NavLink to={"/create"}>Create Section</NavLink>
                    <NavLink to={"/delete"}>Delete Section</NavLink>

                </nav>
                
                <ButtonAccount />
            </HeaderContent>
            


        </HeaderContainer>
   
    )
}