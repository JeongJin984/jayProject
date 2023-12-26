package com.jay.memberserver.domain.converter;

import com.jay.memberserver.domain.entity.MemberType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.util.StringUtils.hasText;

@Converter
@Slf4j
public class MemberTypeConverter implements AttributeConverter<MemberType, String> {
    @Override
    public String convertToDatabaseColumn(MemberType memberType) {
        if(memberType == null) return null;
        return memberType.getCode();
    }

    @Override
    public MemberType convertToEntityAttribute(String code) {
        if(!hasText(code)) return null;
        try {
            return MemberType.fromCode(code);
        } catch (IllegalArgumentException e) {
            log.error("failed to convert caused by unexpected coe {}", code);
            throw e;
        }
    }
}
