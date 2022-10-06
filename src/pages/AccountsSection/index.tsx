import { SearchForm, SearchButton, Counter, WrapperAccount, EmptyAccount } from "./styles";
import { AiOutlineSearch } from 'react-icons/ai'
import { AccountCard } from "../../components/AccountCard";
import { BsClipboardMinus } from "react-icons/bs";
import axios from 'axios'
import { useEffect, useState } from "react";

import * as z from 'zod'
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod"
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

const searchFormSchema = z.object({
  query: z.string(),
})

type SearchFormInput = z.infer<typeof searchFormSchema>






export function AccountsSection() {
  const [accounts, setAccounts] = useState<Account[]>([]);
  const [filteredAccounts, setFilteredAccounts] = useState<Account[]>([]);
  const { register, handleSubmit } = useForm<SearchFormInput>({
    resolver: zodResolver(searchFormSchema),
  })

  useEffect(() => {
    Aos.init({ duration: 1500})
  }, [])


  useEffect(() => {
    axios.get("http://localhost:8080/api/v1/accounts")
    .then((response) => {
      setAccounts(response.data.content);
      setFilteredAccounts(response.data.content)

    });
  }, []);
 

 

  function handleSearchAccount(data: SearchFormInput){
    const filterAccount = accounts.filter((account) => account.id == Number(data.query))
    console.log(data.query)
    if(data.query){
      setFilteredAccounts(filterAccount)

    } else {
      setFilteredAccounts(accounts)
    }
     

  }


  
  return (
    < >
      <SearchForm 
      data-aos="fade-up"
      onSubmit={handleSubmit(handleSearchAccount)}
      >
        <input type="number" placeholder="Buscar Conta [ID]" {...register("query")} />
        <SearchButton>
          <button className="buttonSearch" type="submit">
            Buscar Conta
            <AiOutlineSearch color="f2f2f2" />
          </button>
        </SearchButton>
      </SearchForm>


      <Counter data-aos="fade-up">
        <header className="header">
          <div>
            <p>Accounts Created</p>
            <span>{accounts.length}</span>
          </div>
        </header>


        
        <WrapperAccount>
          {
            filteredAccounts.map(repo => (
              <AccountCard
              key={repo.id}
              id={repo.id}
              agency={repo.agencyCode}
              account={repo.accountCode}
              digit={repo.digitVerification}
              card={repo.cards}
              name={repo.nameOwner}  
              />
            ) )

            
          }
        </WrapperAccount>

          {accounts.length <=0 && (
            <EmptyAccount>
            <BsClipboardMinus size={50} />
            <div>
              <p>O nosso banco ainda não tem nenhuma conta</p>
              <span>Crie sua conta agora e faça parte da Synchro</span>
            </div>
          </EmptyAccount>
          ) }

       

      </Counter>


      

    </>
  );
}
