import axios from "axios";
import { useEffect, useState } from "react";
import { BsClipboardX } from "react-icons/bs";
import { DeleteCard } from "../../components/DeleteCard";
import { EmptyAccount } from "../AccountsSection/styles";
import { Counter, WrapperAccount } from "./styles";
import Aos from 'aos'
import 'aos/dist/aos.css'

type Account = {
  id: number;
  nameOwner: string;
  agencyCode: string;
  accountCode: string;
  digitVerification: string;
  register_id: string;
  cards: [
    {
      id: number;
      name: string;
      flag: string;
      number: string;
      digitCode: string;
      limitBalance: number;
      typeCard: {
            id: number;
            name: string;
        }
    }
  ]

}


export function DeleteSection() {

  const [accounts, setAccounts] = useState<Account[]>([]);

  useEffect(() => {
    Aos.init({ duration: 1500})
  }, [])

  useEffect(() => {
    axios.get("http://localhost:8080/api/v1/accounts")
    .then((response) => {
      setAccounts(response.data.content);

    });
  }, []);
  
  return (
    <>
      <Counter data-aos="fade-up">
        <header className="header">
          <div>
            <p>Delete Accounts</p>
            <span>{accounts.length}</span>
          </div>
        </header>

        <WrapperAccount>
          {accounts.map(repo =>(
            <DeleteCard
            key={repo.id}
            id={repo.id}
            agency={repo.agencyCode}
            account={repo.accountCode}
            digit={repo.digitVerification}
            card={repo.cards}
            name={repo.nameOwner}
            onDelete={() => {
              setAccounts((oldAccounts) => {
                return oldAccounts.filter((oldAccount) => oldAccount.id !== repo.id )
              })
              
            }} />
          ))}

        </WrapperAccount>


        {accounts.length <=0 && (
            <EmptyAccount>
            <BsClipboardX size={50} />
            <div>
              <p>O nosso banco ainda não tem nenhuma conta para ser deletada</p>
              <span>Crie sua conta agora e faça parte da Synchro</span>
            </div>
          </EmptyAccount>
          ) }
      </Counter>
    </>
  );
}
