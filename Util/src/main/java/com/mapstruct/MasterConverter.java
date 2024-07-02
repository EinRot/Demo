package com.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO 对象转换
 * 　　* @date 2021/10/25
 */
@Mapper
//@Mapper(componentModel = "spring")
public interface MasterConverter {
    MasterConverter INSTANCE = Mappers.getMapper(MasterConverter.class);

    //机位动态转机位匹配
    @Mappings({
            @Mapping(target = "flightId", source = "flightId"),
            @Mapping(target = "lastFlightId", ignore = true)
    })
    BerthMaster berthToBerthConvert(BerthDynamic berthDynamic);


}
