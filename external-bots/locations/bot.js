I18n.translations = {
    en: {
        location_title: 'Location',
        location_suggestions_title: 'Send location',
        location_description: 'Share your location',
        location_address: 'address'
    },
    ru: {
        location_title: 'Местоположение',
        location_description: 'Поделитесь своим местоположением',
        location_address: 'Адрес'
    },
    af: {
        location_title: 'Ligging',
        location_description: 'Deel jou ligging',
        location_address: 'Addres'
    },
    ar: {
        location_title: 'الموقع',
        location_description: 'شارك موقعك',
        location_address: 'العنوان'
    },
    'zh-hant': {
        location_title: '位置',
        location_description: '分享您的位置',
        location_address: '地址'
    },
    'zh-hans': {
        location_title: '位置',
        location_description: '分享你的位置',
        location_address: '地址'
    },
    'zh-yue': {
        location_title: '所在位置',
        location_description: '分享所在位置',
        location_address: '地址'
    },
    'zh-wuu': {
        location_title: '位置',
        location_description: '分享您的位置',
        location_address: '地址'
    },
    nl: {
        location_title: 'Locatie',
        location_description: 'Deel je locatie',
        location_address: 'Adres'
    },
    fr: {
        location_title: 'Emplacement',
        location_description: 'Partager votre emplacement',
        location_address: 'Adresse'
    },
    de: {
        location_title: 'Ort',
        location_description: 'Teilen Sie Ihren Ort',
        location_address: 'Adresse'
    },
    hi: {
        location_title: 'स्थान',
        location_description: 'अपना स्थान साझा करें',
        location_address: 'पता'
    },
    hu: {
        location_title: 'Helyszín',
        location_description: 'Helyszín megosztása',
        location_address: 'Cím'
    },
    it: {
        location_title: 'Posizione',
        location_description: 'Condividi la tua posizione',
        location_address: 'Indirizzo'
    },
    ja: {
        location_title: '位置',
        location_description: '位置情報を共有',
        location_address: 'アドレス'
    },
    ko: {
        location_title: '위치',
        location_description: '내 위치 공유하기',
        location_address: '주소'
    },
    pl: {
        location_title: 'Lokalizacja',
        location_description: 'Udostępnij swoją lokalizację',
        location_address: 'Adres'
    },
    'pt-br': {
        location_title: 'Localização',
        location_description: 'Compartilhar sua localização',
        location_address: 'Endereço'
    },
    'pt-pt': {
        location_title: 'Location',
        location_description: 'Partilhar a sua localização',
        location_address: 'Endereço'
    },
    ro: {
        location_title: 'Locație',
        location_description: "Partajează locația",
        location_address: 'Adresă'
    },
    sl: {
        location_title: 'Lokacija',
        location_description: 'Deli svojo lokacijo',
        location_address: 'Naslov'
    },
    es: {
        location_title: 'Ubicación',
        location_description: 'Comparte tu ubicación',
        location_address: 'Dirección'
    },
    'es-ar': {
        location_title: 'Ubicación',
        location_description: 'Comparte tu ubicación',
        location_address: 'Dirección'
    },
    sw: {
        location_title: 'Eneo',
        location_description: 'Shiriki eneo lako',
        location_address: 'Anwani'
    },
    sv: {
        location_title: 'Plats',
        location_description: 'Dela din plats',
        location_address: 'Adress'
    },
    'fr-ch': {
        location_title: 'Emplacement',
        location_description: 'Partagez votre emplacement',
        location_address: 'Adresse'
    },
    'de-ch': {
        location_title: 'Standort',
        location_description: 'Teile deinen Standort',
        location_address: 'Adresse'
    },
    'it-ch': {
        location_title: 'Posizione',
        location_description: 'Condividi la tua posizione',
        location_address: 'Indirizzo'
    },
    th: {
        location_title: 'ตำแหน่ง',
        location_description: 'แชร์ตำแหน่งของคุณ',
        location_address: 'ที่อยู่'
    },
    tr: {
        location_title: 'Konum',
        location_description: 'Konumunuzu paylaşın',
        location_address: 'Adres'
    },
    uk: {
        location_title: 'Місцезнаходження',
        location_description: 'Поділіться своїм місцезнаходженням',
        location_address: 'Адреса'
    },
    ur: {
        location_title: 'مقام',
        location_description: 'اپنا مقام بتائیں',
        location_address: 'پتہ'
    },
    vi: {
        location_title: 'Vị trí',
        location_description: 'Chia sẻ vị trí của bạn',
        location_address: 'Địa chỉ'
    }
};

function locationsSuggestions (params) {
    var result = {title: "Send location"};
    var seqArg = params.seqArg ? params.seqArg : "";

    if (seqArg == "Dropped pin")
    {
        result.showBack = true;
        result.height = "max";
        result.markup = ["view", {},
                            ['dropped-pin']];
    }
    else if (seqArg != "")
    {
        result.showBack = true;
        result.markup = ['scroll-view', {keyboardShouldPersistTaps: "always"},
                            ['view', {},
                                ['places-search']]];
    }
    else
    {
        result.markup = ['scroll-view', {keyboardShouldPersistTaps: "always"},
                            ['view', {},
                                ['current-location-map'],
                                ['current-location'],
                                ['separator'],
                                ['places-nearby']]];
    }

    return result;
}

status.command({
    name: "location",
    title: I18n.t('location_title'),
    description: I18n.t('location_description'),
    sequentialParams: true,
    hideSendButton: true,
    params: [{
        name: "address",
        type: status.types.TEXT,
        placeholder: I18n.t('location_address'),
        suggestions: locationsSuggestions
    }],
    preview: function (params) {
        var text = status.components.text(
            {
                style: {
                    marginTop: 0,
                    marginHorizontal: 0,
                    fontSize: 15,
                    lineHeight: 23,
                    fontFamily: "font",
                    color: "black"
                }
            }, params.address);
        var uri = "https://maps.googleapis.com/maps/api/staticmap?center="
            + params.address
            + "&size=100x100&maptype=roadmap&key=AIzaSyBNsj1qoQEYPb3IllmWMAscuXW0eeuYqAA&language=en"
            + params.address;

        var image = status.components.image(
            {
                source: {uri: uri},
                style: {
                    borderRadius: 5
                    marginTop: 12
                    height:    58
                }
            }
        );

        return {markup: ['view', {},
                            text,
                            ['view', {},
                                image,
                                ['view', {style: {position: "absolute",
                                                  top: 0,
                                                  right: 0,
                                                  bottom: 0,
                                                  left: 0,
                                                  justifyContent: "center",
                                                  alignItems: "center"}},
                                    ['view', {style: {borderColor:  "#628fe3",
                                                      backgroundColor: "#FFFFFF",
                                                      borderWidth:  4,
                                                      borderRadius: 8,
                                                      height:       15,
                                                      width:        15}}]]]]};
    },
    shortPreview: function (params) {
        return {
            markup: status.components.text(
                {},
                I18n.t('location_title') + ": " + params.address
            )
        };
    }
});