package com.nordicmotorhome.motorhomerentals.domain.services;

import com.nordicmotorhome.motorhomerentals.data.DataFacadeImpl;
import com.nordicmotorhome.motorhomerentals.data.IDataFacade;
import com.nordicmotorhome.motorhomerentals.data.Message;
import com.nordicmotorhome.motorhomerentals.domain.MessageType;
import com.nordicmotorhome.motorhomerentals.domain.entities.MotorhomeEntity;
import com.nordicmotorhome.motorhomerentals.domain.entities.MotorhomeModelEntity;
import com.nordicmotorhome.motorhomerentals.domain.entities.RentalEntity;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;
import com.nordicmotorhome.motorhomerentals.domain.mappers.IEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.mappers.MotorhomeEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.mappers.MotorhomeModelEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.UI.dto.MotorhomeSearchDTO;
import com.nordicmotorhome.motorhomerentals.UI.model.MotorhomeModel;
import com.nordicmotorhome.motorhomerentals.UI.model.MotorhomeModelModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Is used for servicing and performing 'commands' given from DomainFacadeImpl, always returning a Message

public class MotorhomeService {
    IDataFacade dataFacade = new DataFacadeImpl();

    IEntityModelMapper<MotorhomeModelEntity, MotorhomeModelModel> mmemm = new MotorhomeModelEntityModelMapper();
    IEntityModelMapper<MotorhomeEntity, MotorhomeModel> memm = new MotorhomeEntityModelMapper();

    //Author : AML
    public Message searchMotorhomes(int beds) {
        try {
            List<MotorhomeModelModel> list = mmemm.mapAllToModel(dataFacade.findAllMotorhomeModels("beds", beds));
            return new Message(MessageType.SUCCESS, list);
        }catch (NoSuchEntityException e) {
            return new Message(MessageType.ERROR, "No motorhomes found with '" + beds + "' beds");
        }
    }

    // Author: RAP
    public Message searchMotorhomes(int beds, LocalDate startDate, LocalDate endDate) {
        try {
            ArrayList<MotorhomeModelEntity> motorhomeModelEntities = (ArrayList<MotorhomeModelEntity>) dataFacade.findAllMotorhomeModels("beds", beds);
            ArrayList<MotorhomeEntity> candidates = new ArrayList<>();

            for (MotorhomeModelEntity model : motorhomeModelEntities) {
                ArrayList<MotorhomeEntity> motorhomeEntities = (ArrayList<MotorhomeEntity>) dataFacade.findAllMotorhomes("model_id", model.getID());

                for (MotorhomeEntity entity : motorhomeEntities) {
                    try {
                        ArrayList<RentalEntity> rentalEntities = (ArrayList<RentalEntity>) dataFacade.findAllRentals("motorhome_id", entity.getID());

                        for (RentalEntity re : rentalEntities) {
                            if (re.getStartDate().isBefore(startDate) && re.getEndDate().isAfter(startDate)) continue;
                            if (re.getStartDate().isBefore(endDate) && re.getEndDate().isAfter(endDate)) continue;
                            if (re.getStartDate().isBefore(endDate) && re.getEndDate().isAfter(startDate)) continue;
                            if (re.getStartDate().isBefore(startDate) && re.getEndDate().isAfter(endDate)) continue;

                            candidates.add(re.getMotorhomeEntity());
                        }
                    } catch (NoSuchEntityException e) {
                        candidates.add(entity);
                    }
                }
            }

            ArrayList<MotorhomeSearchDTO> dtos = new ArrayList<>();

            for (MotorhomeEntity candidate : candidates) {
                dtos.add(new MotorhomeSearchDTO(memm.mapToModel(candidate), candidate.getPriceByRentalLength(startDate, endDate)));
            }

            return new Message(MessageType.SUCCESS, dtos);
        } catch (NoSuchEntityException e) {
            return new Message(MessageType.ERROR, "No motorhomes were found matching the criteria");
        }
    };
}
