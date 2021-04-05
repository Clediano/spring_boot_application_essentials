package com.devdojo.springbootessentials.mapper;

import com.devdojo.springbootessentials.domain.Anime;
import com.devdojo.springbootessentials.requests.AnimePostRequestBody;
import com.devdojo.springbootessentials.requests.AnimePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") //componentModel para caso necessite injetar em alguma classe
public abstract class AnimeMapper {
    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    public abstract Anime toAnime(AnimePostRequestBody animePostRequestBody);

    public abstract Anime toAnime(AnimePutRequestBody animePutRequestBody);
}
