====================================
DormitoryService API dokumentatsioon
====================================

**DormitoryService API** võimaldab lisada ja pärida andmeid ühiselamu teenuselt. Teenuse kasutajaks on ühiselamute ja nende elanike haldajad. Ülemressursiks on dormitory *(ühiselamu)* nind selle alamressursiks tenant *(elanik)*.

Kõikidele klientidele on väljastatud ühine **token**, mis kehtib mõlema API puhul ning tuleb kaasa anda igale päringule. Vale tokeni korral väljastatakse veateade ning operatsioon ei õnnestu.

Selleks, et tuvastada topelt saadud päringuid, on kõik andmeid muutvad päringud varustatud identifikaatoriga, mis on ühe kliendi piires unikaalsed. Teist korda identse päringu saamisel tagastatakse eelnevalt koostatud vastus uuesti.

REST ja SOAP API kasutavad ühte ja sama veebiteenust ning kõik muudatused, mis ühe API kaudu tehakse, on nähtavad teises. Seetõttu on ka sisend- ja väljundandmed väga sarnased.


SOAP API
========

**Teenuse nimetus:** dormitoryService1

**API token:** asd

**Disainipõhimõtted:** XSD loomisel on kasutatud Venetian Blind mustrit: kõik tüübid on globaalsed ja elemendid on lokaalsed. Operatsioonid ja elemendid on nimetatud lower camelCase stiilis ning on teineteisega vastavuses (näiteks operatsioonile getTenant vastab getTenantResponse, mille põhiliseks sisuks on TenantType jne). Lähtutud on Clean Code põhimõtetest.

Kui pole mainitud teisiti, on elementide puhul tegu XML Schema andmetüüpidega. Lisaks nenedele on defineeritud järgnevate elementide jaoks eraldi lihttüübid (*simpleType*) ja komplektrüübid (*complexType*).


Lihttüübid:

**idCodeType** integer. 11 numbrimärgist koosnev arv.

**statusType** string. Võimalikud väärtused: "active", "inactive".

**genderType** string. Võimalikud väärtused: "male", "female", "other".

**dormitoryConditionType** string. Võimalikud väärtused: "renovated", "new", "old".

**hasRelatedTenantsType** string. Võimalikud väärtused: "yes", "no".


Komplekstüübid:

**dormitoryType**

- **id** integer. *(mittekohustuslik)* Ühiselamu unikaalne identifikaator, mis määratakse süsteemi poolt automaatselt.
- **administrativeArea** string. Asula, mille haldusalasse ühiselamu jääb. Võib olla linn, küla. Näiteks Tallinn, Tartu.
- **dormitoryAddress** string. Ühiselamu aadress.
- **dormitoryCapacity** integer. Ühiselamu mahutavus arvuliselt ehk mitu kohta ühiselamu maksimaalselt pakub.
- **dormitoryOwner** string. Asutus või isik, kelle valdusesse ühiselamu kuulub.
- **dormitoryCondition** dormitoryConditionType. Ühiselamu olukord.
- **dormitoryTenantList** dormitoryTenantListType. Nimekiri ühiselamu elanikest.

**tenantType**

- **id** integer. *(mittekohustuslik)* Elaniku unikaalne identifikaator, mis määratakse süsteemi poolt automaatselt.
- **firstName** string. Elaniku eesnimi.
- **lastName** string. Elaniku perenimi.
- **idCode** idCodeType. Elaniku isikukood.
- **gender** genderType. Elaniku sugu.
- **studentStatus** statusType. Elaniku tudengistaatus.
- **university** string. Ülikool, mille nimekirjas elanik on.

**dormitoryTenantType**

- **tenant** tenantType. Vaadeldava ühiselamu elanik.
- **startDate** date. Vaadeldavas ühiselamus üüriperioodi alustamise kuupäev.
- **endDate** date. Üüriperioodi lõpukuupäev.
- **status** statusType. Üürilepingu staatus. Aktiivne, kui leping on kehtiv, mitteaktiivne, kui leping ei ole kehtiv.

**dormitoryTenantListType**
- **dormitoryTenant** dormitoryTenantType. *(mittekohustuslik)* Vaadeldava ühiselamu elanike nimekirja kuuluv elanik.








REST API
========

**Teenuse nimetus:** dormitoryService1

**API token:** asd

**Disainipõhimõtted:** REST API pöördub SOAP-teenuse poole, teisendades saadud andmed vajalikule kujule. Lisafunktsionaalsusi ei ole, kõik operatsioonid on samasugused. Päring ja vastus peavad olema JSON-formaadis.


Abistavad tüübid:

**idCodeType** integer. 11 numbrimärgist koosnev arv.

**statusType** string. Võimalikud väärtused: "active", "inactive".

**genderType** string. Võimalikud väärtused: "male", "female", "other".

**dormitoryConditionType** string. Võimalikud väärtused: "renovated", "new", "old".

**hasRelatedTenantsType** string. Võimalikud väärtused: "yes", "no".

**dormitoryTenantListType**
- **dormitoryTenant** dormitoryTenantType. *(mittekohustuslik)* Vaadeldava ühiselamu elanike nimekirja kuuluv elanik.


Kolm põhilist JSON-formaadis objekti:

**dormitoryType**

- **id** integer. *(mittekohustuslik)* Ühiselamu unikaalne identifikaator, mis määratakse süsteemi poolt automaatselt.
- **administrativeArea** string. Asula, mille haldusalasse ühiselamu jääb. Võib olla linn, küla. Näiteks Tallinn, Tartu.
- **dormitoryAddress** string. Ühiselamu aadress.
- **dormitoryCapacity** integer. Ühiselamu mahutavus arvuliselt ehk mitu kohta ühiselamu maksimaalselt pakub.
- **dormitoryOwner** string. Asutus või isik, kelle valdusesse ühiselamu kuulub.
- **dormitoryCondition** dormitoryConditionType. Ühiselamu olukord.
- **dormitoryTenantList** dormitoryTenantListType. Nimekiri ühiselamu elanikest.

**tenantType**

- **id** integer. *(mittekohustuslik)* Elaniku unikaalne identifikaator, mis määratakse süsteemi poolt automaatselt.
- **firstName** string. Elaniku eesnimi.
- **lastName** string. Elaniku perenimi.
- **idCode** idCodeType. Elaniku isikukood.
- **gender** genderType. Elaniku sugu.
- **studentStatus** statusType. Elaniku tudengistaatus.
- **university** string. Ülikool, mille nimekirjas elanik on.

**dormitoryTenantType**

- **tenant** tenantType. Vaadeldava ühiselamu elanik.
- **startDate** date. Vaadeldavas ühiselamus üüriperioodi alustamise kuupäev.
- **endDate** date. Üüriperioodi lõpukuupäev.
- **status** statusType. Üürilepingu staatus. Aktiivne, kui leping on kehtiv, mitteaktiivne, kui leping ei ole kehtiv.

