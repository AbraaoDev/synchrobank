import { ButtonContainer } from "./styles";
import { TbReportMoney } from 'react-icons/tb'
export function ButtonAccount(){
    return(
        <>
            <ButtonContainer>
                <button className="buttonAcc" type="button">
                    <TbReportMoney color="04D361" />
                    Criar Conta</button>
            </ButtonContainer>
        </>
    )
}