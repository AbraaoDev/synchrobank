import axios from "axios";
import { useEffect, useState } from "react";
import { TbTrash } from "react-icons/tb";
import { ButtonDelete, Container, CounterCard } from "./styles";

interface CardProps {
  id: number;
  digitCode: string;
  flag: string;
  name: string;
  number: string;
  typeCard: {
    id: number;
    name: string;
  };
}

interface AccountCardProps {
  id: number;
  agency: string;
  account: string;
  digit: string;
  card: CardProps[];
  name: string;
  onDelete: () => void;
}


export function DeleteCard(props: AccountCardProps) {
  // const [accounts, setAccounts] = useState<AccountCardProps[]>([])

  // useEffect(() => {
  //   loadAccounts()
  // }, [])

  // async function loadAccounts(){
  //   const response = await axios.get('http://localhost:8080/api/v1/accounts')
  //   setAccounts(response.data)
  // }

  async function deleteAccount(id: number){
    await axios.delete(`http://localhost:8080/api/v1/accounts/${id}`)
    props.onDelete()

  }



  return (
    <>
      <Container>
        <span>{props.id}</span>
        <p className="name">{props.name}</p>
        <p>{props.agency}</p>
        <p>{props.account} - {props.digit}</p>

        <CounterCard>
          <p>Cartões Cadastrados</p>
          <span>{props.card.length}</span>
        </CounterCard>

        <ButtonDelete onClick={() => deleteAccount(props.id)}>
            <TbTrash size={20}/>
        </ButtonDelete>

      </Container>
    </>
  );
}
