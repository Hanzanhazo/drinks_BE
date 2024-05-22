package com.goormfj.hanzan.recipe.dto;

import lombok.Data;

@Data
public class TypeDTO {
    private boolean sojuType;
    private boolean beerType;
    private boolean wineType;
    private boolean sakeType;
    private boolean vodkaType;
    private boolean whiskeyType;
    private boolean makgeolliType;

    public boolean isSojuType() { return sojuType; }
    public void setSojuType(boolean sojuType) { this.sojuType = sojuType; }

    public boolean isBeerType() { return beerType; }
    public void setBeerType(boolean beerType) { this.beerType = beerType; }

    public boolean isWineType() { return wineType; }
    public void setWineType(boolean wineType) { this.wineType = wineType; }

    public boolean isSakeType() { return sakeType; }
    public void setSakeType(boolean sakeType) { this.sakeType = sakeType; }

    public boolean isVodkaType() { return vodkaType; }
    public void setVodkaType(boolean vodkaType) { this.vodkaType = vodkaType; }

    public boolean isWhiskeyType() { return whiskeyType; }
    public void setWhiskeyType(boolean whiskeyType) { this.whiskeyType = whiskeyType; }

    public boolean isMakgeolliType() { return makgeolliType;}
    public void setMakgeolliType(boolean makgeolliType) { this.makgeolliType = makgeolliType;}

}
