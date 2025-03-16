package com.rpbank.rpcadastro.mapper;

import com.rpbank.rpcadastro.domain.dto.RequestSaveNewAccountDTO;
import com.rpbank.rpcadastro.domain.entity.CadastroPF;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CadastroPFMapper {

    public CadastroPF toTbCadastroPf(RequestSaveNewAccountDTO requestSaveNewAccountDTO){

       return CadastroPF.builder()
                .nmPessoa(requestSaveNewAccountDTO.getNmPessoa())
                .cpfPessoa(requestSaveNewAccountDTO.getCpfPessoa())
                .rgPessoa(requestSaveNewAccountDTO.getRgPessoa())
                .build();


    }
}
