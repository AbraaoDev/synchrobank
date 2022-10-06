import { ButtonHomeContainer } from './styles';
import { BsPersonPlusFill } from 'react-icons/bs'

export function ButtonHome(){
    return(
        <>
            <ButtonHomeContainer>
                <button className="buttonHo" type="button">
                    <BsPersonPlusFill color="0D0D0D" />
                    Cadastrar</button>
            </ButtonHomeContainer>
        </>
    )
}