//package com.dealfinder.dealfinderprocessor.mapper;
//
//import com.dealfinder.dealfindercommon.dto.sale.rewards.RewardDto;
//import com.dealfinder.dealfindercommon.model.Reward;
//import org.mapstruct.Mapper;
//
//import java.util.Objects;
//
//@Mapper(componentModel = "spring")
//public class RewardMapper {
//
//    public Reward toCreationEntity(RewardDto rewardDto) {
//        Reward newReward = new Reward();
//        newReward.setRewardText(Objects.requireNonNullElse(rewardDto.rewardText, "defaultName"));
//        //todo add sale repository and get sale by saleId
////        newReward.setSale(rewardDto.sale);
//        return newReward;
//    }
//
//
//    public void updateRewardByNotNullFieldsOfRewardDto(Reward rewardToChange, RewardDto rewardDto) {
//        if (rewardDto.rewardText != null){
//            rewardToChange.setRewardText(rewardDto.rewardText);
//        }
//    }
//}
