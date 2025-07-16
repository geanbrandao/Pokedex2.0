package br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components

//@Composable
//fun PokemonTypesView(
//    typeOne: PokemonTypeModel,
//    typeTwo: PokemonTypeModel?,
//    isLarge: Boolean,
//    modifier: Modifier = Modifier,
//) {
//    PokemonTypes(typeOne = typeOne, typeTwo = typeTwo, isLarge = isLarge, modifier = modifier)
//}

//@Composable
//private fun PokemonTypes(
//    modifier: Modifier = Modifier,
//    typeOne: PokemonTypeModel = PokemonTypeModel.Grass,
//    typeTwo: PokemonTypeModel? = PokemonTypeModel.Poison,
//    isLarge: Boolean = false,
//) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = modifier,
//    ) {
//        PokemonType(type = typeOne, isLarge = isLarge)
//        Spacer(modifier = Modifier.size(size = PaddingOne))
//        typeTwo?.let {
//            PokemonType(type = it, isLarge = isLarge)
//        }
//    }
//}

//@Composable
//fun PokemonType(
//    type: PokemonTypeModel,
//    isLarge: Boolean,
//) {
//    if (isLarge) {
//        PokemonType(
//            type = type,
//            iconSize = IconTypeLargeSize,
//            style = AppTypography.bodyLarge,
//        )
//    } else {
//        PokemonType(
//            type = type,
//            iconSize = IconTypeSmallSize,
//            style = AppTypography.labelMedium,
//        )
//    }
//}

//@Preview(showBackground = true)
//@Composable
//private fun PokemonTypesPreview() {
//    Column {
//        PokemonTypes()
//        PokemonTypes(isLarge = true)
//    }
//}