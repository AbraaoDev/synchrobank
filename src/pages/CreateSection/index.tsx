import { Container, Content, FormSynchro } from "./styles";
import  synchroCreate  from '../../assets/logo.svg'
import {RiErrorWarningLine} from 'react-icons/ri'
import { ChangeEvent, useEffect, useState } from "react";
import axios from "axios";
import { toast } from "react-toastify";
import Aos from 'aos'
import 'aos/dist/aos.css'

interface IAccount {
    nameOwner: string, 
    agencyCode: string, 
    accountCode: string, 
    digitVerification: string, 
    registerId: string;
    cards: [{
        name: string,
        flag: string,
        number: string,
        digitCode: string,
        limitBalance: number,
        typeCard: {
            id: number;
            name: string;
        };


    }]

}


export function CreateSection(){

    useEffect(() => {
        Aos.init({ duration: 1500})
      }, [])
    

    function generateCard(lenght: number){
        return Math.floor(Math.pow(10, lenght-1) + Math.random() *9 * Math.pow(10, lenght-1))
    }


    const [model, setModel] = useState<IAccount>({
        nameOwner: "",
        agencyCode: "",
        accountCode: "",
        digitVerification: "",
        registerId: "",
        cards: [{
            name: "NewCard",
            flag: "MASTERCARD",
            number: String(generateCard(16)),
            digitCode: "785",
            limitBalance: 1000.00,
            typeCard: {
                id: 2,
                name: "CREDIT CARD",
            }
        }]
       

    })


    function updatedModel (e: ChangeEvent<HTMLInputElement>){
        setModel({
            ...model,
            [e.target.name]:e.target.value
        })
    }

    async function onSubmit(e: ChangeEvent<HTMLFormElement>){
        e.preventDefault()
        try{
            const response = await axios.post('http://localhost:8080/api/v1/accounts/', model)
            toast.success("Conta criada com sucesso!")
        }catch(err){
            toast.error(`Error: ${err}`)
        }


    }

    return(
        <>
            <Container data-aos="fade-up">
                <Content>
                    <img src={synchroCreate} alt="Logo Synchro" />
                    <h1>Bom que estamos aqui,    crie agora sua conta</h1>
                    <div>
                        <RiErrorWarningLine size={30} color="#04D361"/>
                        <span>Seu cartão será gerado automaticamente</span>
                    </div>
                    
                </Content>

                <FormSynchro onSubmit={onSubmit}>
                    <h1>Informações</h1>
                    <input 
                    type="text" 
                    name="nameOwner" 
                    placeholder="Nome Completo" 
                    onChange={(e: ChangeEvent<HTMLInputElement>) => updatedModel(e)}/>
                    <div>
                        <input 
                        type="number" 
                        name="agencyCode" 
                        placeholder="Agência"
                        onChange={(e: ChangeEvent<HTMLInputElement>) => updatedModel(e)}/>
                        <input 
                        type="number" 
                        name="accountCode" 
                        placeholder="Conta"
                        onChange={(e: ChangeEvent<HTMLInputElement>) => updatedModel(e)}/>
                        <input 
                        type="number" 
                        name="digitVerification" 
                        placeholder="Dígito"
                        onChange={(e: ChangeEvent<HTMLInputElement>) => updatedModel(e)}/>

                    </div>

                    <input 
                    type="number" 
                    name="registerId" 
                    placeholder="CPF"
                    onChange={(e: ChangeEvent<HTMLInputElement>) => updatedModel(e)}/>
                    <button type="submit">Criar Conta</button>
                </FormSynchro>

            </Container>


        </>
    )
}