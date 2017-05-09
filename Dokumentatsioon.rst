====================================
DormitoryService API dokumentatsioon
====================================

:Autor: Eva Maria Veitmaa
:Loomise aeg: 07/05/2017
:Aine: Veebiteenused IDU0075, Tallinna Tehnikaülikool

**DormitoryService API** võimaldab lisada ja pärida andmeid ühiselamu teenuselt. Teenuse kasutajaks on ühiselamute töötajad, ühiselamute ja nende elanike haldajad. Ülemressursiks on dormitory *(ühiselamu)* nind selle alamressursiks tenant *(elanik)*.

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


addDormitory
------------

Operatsioon uue ühiselamu lisamiseks.

**Sisend:** addDormitoryRequest

- **token** string. Kliendi autentimiseks kasutatav kood ehk *token*.
- **requestCode** integer. Päringu unikaalne identifikaator.
- **administrativeArea** string. Asula, mille haldusalasse ühiselamu jääb. Võib olla linn, küla. Näiteks Tallinn, Tartu.
- **dormitoryAddress** string. Ühiselamu aadress.
- **dormitoryCapacity** integer. Ühiselamu mahutavus arvuliselt ehk mitu kohta ühiselamu maksimaalselt pakub.
- **dormitoryOwner** string. Asutus või isik, kelle valdusesse ühiselamu kuulub.
- **dormitoryCondition** dormitoryConditionType. Ühiselamu olukord.

**id** pole määratud, kuna see genereeritakse automaatselt süsteemi poolt.

.. code-block:: xml

    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:dor="http://www.ttu.ee/idu0075/2015/ws/dormitory">
       <soapenv:Header/>
       <soapenv:Body>
          <dor:addDormitoryRequest>
             <dor:token>asd</dor:token>
             <dor:requestCode>1</dor:requestCode>
             <dor:administrativeArea>Tallinn</dor:administrativeArea>
             <dor:dormitoryAddress>Akadeemia tee 11/2, Mustamäe</dor:dormitoryAddress>
             <dor:dormitoryCapacity>240</dor:dormitoryCapacity>
             <dor:dormitoryOwner>TTU</dor:dormitoryOwner>
             <dor:dormitoryCondition>renovated</dor:dormitoryCondition>
          </dor:addDormitoryRequest>
       </soapenv:Body>
    </soapenv:Envelope>

**Väljund:** addDormitoryResponse

- **responseCode** integer. Päringust saadud unikaalne identifikaator, kontrollimaks *Idempotent Capability* mustrit.
- **dormitory** dormitoryType. Päringu põhjal genereeritud ühiselamuobjekt. Sisaldab päringus antud infot ja lisaks ühiselamule antud **id** väärtust.

.. code-block:: xml

    <S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
        <S:Body>
           <addDormitoryResponse xmlns="http://www.ttu.ee/idu0075/2015/ws/dormitory">
              <responseCode>1</responseCode>
              <dormitory>
                 <id>1</id>
                 <administrativeArea>Tallinn</administrativeArea>
                 <dormitoryAddress>Akadeemia tee 11/2, Mustamäe</dormitoryAddress>
                 <dormitoryCapacity>240</dormitoryCapacity>
                 <dormitoryOwner>TTU</dormitoryOwner>
                 <dormitoryCondition>renovated</dormitoryCondition>
                 <dormitoryTenantList/>
              </dormitory>
           </addDormitoryResponse>
        </S:Body>
    </S:Envelope>

getDormitory
------------

Operatsioon ühe ühiselamu küsimiseks talle määratud ID põhjal.

**Sisend:** getDormitoryRequest

- **token** string. Kliendi autentimiseks kasutatav kood ehk *token*.
- **id** integer. Ühiselamu unikaalne identifikaator.

.. code-block:: xml

    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:dor="http://www.ttu.ee/idu0075/2015/ws/dormitory">
       <soapenv:Header/>
       <soapenv:Body>
          <dor:getDormitoryRequest>
             <dor:token>asd</dor:token>
             <dor:id>1</dor:id>
          </dor:getDormitoryRequest>
       </soapenv:Body>
    </soapenv:Envelope>

**Väljund:** getDormitoryResponse

- **getDormitoryResponse** dormitoryType. Ühiselamu, mille **id** väärtus vastab päringus määratud väärtusele.

.. code-block:: xml

    <S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
       <S:Body>
          <getDormitoryResponse xmlns="http://www.ttu.ee/idu0075/2015/ws/dormitory">
             <id>1</id>
             <administrativeArea>Tallinn</administrativeArea>
             <dormitoryAddress>Akadeemia tee 11/2, Mustamäe</dormitoryAddress>
             <dormitoryCapacity>240</dormitoryCapacity>
             <dormitoryOwner>TTU</dormitoryOwner>
             <dormitoryCondition>renovated</dormitoryCondition>
             <dormitoryTenantList/>
          </getDormitoryResponse>
       </S:Body>
    </S:Envelope>

getDormitoryList
----------------

Operatsioon ühiselamute nimekirja küsimiseks. Otsingut saab filtreerida asula, omaniku, seisundi ja elanike olemasolu järgi.

**Sisend:** getDormitoryListRequest

- **token** string. Kliendi autentimiseks kasutatav kood ehk *token*.
- **administrativeArea** string. *(mittekohustuslik)* Asula, mille haldusalasse ühiselamu jääb. Võib olla linn, küla. Näiteks Tallinn, Tartu.
- **dormitoryOwner** string. *(mittekohustuslik)* Asutus või isik, kelle valdusesse ühiselamu kuulub.
- **dormitoryCondition** dormitoryConditionType. *(mittekohustuslik)* Ühiselamu olukord. Võimalikud väärtused: "renovated", "new", "old".
- **hasRelatedTenants** hasRelatedTenantsType. *(mittekohustuslik)* Võimalikud väärtused: "yes", "no".

.. code-block:: xml

    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:dor="http://www.ttu.ee/idu0075/2015/ws/dormitory">
       <soapenv:Header/>
       <soapenv:Body>
          <dor:getDormitoryListRequest>
             <dor:token>asd</dor:token>
             <!--Optional:-->
             <dor:administrativeArea>Tallinn</dor:administrativeArea>
          </dor:getDormitoryListRequest>
       </soapenv:Body>
    </soapenv:Envelope>

**Väljund:** getDormitoryListResponse

- **dormitory** dormitoryType. *(0 või rohkem)* Ühiselamu, mis on süsteemis registreeritud, omab **id** väärtust ning sobib päringu filtriga.

.. code-block:: xml

    <S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
       <S:Body>
          <getDormitoryListResponse xmlns="http://www.ttu.ee/idu0075/2015/ws/dormitory">
             <dormitory>
                <id>1</id>
                <administrativeArea>Tallinn</administrativeArea>
                <dormitoryAddress>Akadeemia tee 11/2, Mustamäe</dormitoryAddress>
                <dormitoryCapacity>240</dormitoryCapacity>
                <dormitoryOwner>TTU</dormitoryOwner>
                <dormitoryCondition>renovated</dormitoryCondition>
                <dormitoryTenantList/>
             </dormitory>
             <dormitory>
                <id>2</id>
                <administrativeArea>Tallinn</administrativeArea>
                <dormitoryAddress>Raja 4D, Mustamäe</dormitoryAddress>
                <dormitoryCapacity>196</dormitoryCapacity>
                <dormitoryOwner>TTU</dormitoryOwner>
                <dormitoryCondition>renovated</dormitoryCondition>
                <dormitoryTenantList/>
             </dormitory>
          </getDormitoryListResponse>
       </S:Body>
    </S:Envelope>

addTenant
---------

Operatsioon uue elaniku sisestamiseks süsteemi.

**Sisend:** addTenantRequest

- **token** string. Kliendi autentimiseks kasutatav kood ehk *token*.
- **requestCode** integer. Päringu unikaalne identifikaator.
- **firstName** string. Elaniku eesnimi.
- **lastName** string. Elaniku perenimi.
- **idCode** idCodeType. Elaniku isikukood.
- **gender** genderType. Elaniku sugu.
- **studentStatus** statusType. Elaniku tudengistaatus.
- **university** string. Ülikool, mille nimekirjas elanik on.

**id** pole määratud, kuna see genereeritakse automaatselt süsteemi poolt.

.. code-block:: xml

    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:dor="http://www.ttu.ee/idu0075/2015/ws/dormitory">
       <soapenv:Header/>
       <soapenv:Body>
          <dor:addTenantRequest>
             <dor:token>asd</dor:token>
             <dor:requestCode>1</dor:requestCode>
             <dor:firstName>Eva Maria</dor:firstName>
             <dor:lastName>Veitmaa</dor:lastName>
             <dor:idCode>49606064219</dor:idCode>
             <dor:gender>female</dor:gender>
             <dor:studentStatus>active</dor:studentStatus>
             <dor:university>TTU</dor:university>
          </dor:addTenantRequest>
       </soapenv:Body>
    </soapenv:Envelope>

**Väljund:** addTenantResponse

- **responseCode** integer. Päringust saadud unikaalne identifikaator, kontrollimaks *Idempotent Capability* mustrit.
- **tenant** tenantType. Päringu põhjal genereeritud elanikuobjekt. Sisaldab päringus antud infot ja lisaks elanikule antud **id** väärtust.

.. code-block:: xml

    <S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
       <S:Body>
          <addTenantResponse xmlns="http://www.ttu.ee/idu0075/2015/ws/dormitory">
             <responseCode>1</responseCode>
             <tenant>
                <id>1</id>
                <firstName>Eva Maria</firstName>
                <lastName>Veitmaa</lastName>
                <idCode>49606064219</idCode>
                <gender>female</gender>
                <studentStatus>active</studentStatus>
                <university>TTU</university>
             </tenant>
          </addTenantResponse>
       </S:Body>
    </S:Envelope>

getTenant
---------

**Sisend:** getTenantRequest

- **token** string. Kliendi autentimiseks kasutatav kood ehk *token*.
- **id** integer. Elaniku unikaalne identifikaator.

.. code-block:: xml

    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:dor="http://www.ttu.ee/idu0075/2015/ws/dormitory">
       <soapenv:Header/>
       <soapenv:Body>
          <dor:getTenantRequest>
             <dor:token>asd</dor:token>
             <dor:id>1</dor:id>
          </dor:getTenantRequest>
       </soapenv:Body>
    </soapenv:Envelope>

**Väljund:** getTenantResponse

- **getTenantResponse** tenantType. Elanik, kelle **id** väärtus vastab päringus määratud väärtusele.

.. code-block:: xml

    <S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
       <S:Body>
          <getTenantResponse xmlns="http://www.ttu.ee/idu0075/2015/ws/dormitory">
             <id>1</id>
             <firstName>Eva Maria</firstName>
             <lastName>Veitmaa</lastName>
             <idCode>49606064219</idCode>
             <gender>female</gender>
             <studentStatus>active</studentStatus>
             <university>TTU</university>
          </getTenantResponse>
       </S:Body>
    </S:Envelope>

getTenantList
-------------

Operatsioon kõigi elanike nimekirja küsimiseks.

**Sisend:** getTenantListRequest

- **token** string. Kliendi autentimiseks kasutatav kood ehk *token*.
- **firstName** string. *(mittekohustuslik)* Elaniku eesnimi.
- **lastName** string. *(mittekohustuslik)* Elaniku perenimi.
- **gender** genderType. *(mittekohustuslik)* Elaniku sugu. Võimalikud väärtused: "male", "female", "other".
- **studentStatus** statusType. *(mittekohustuslik)* Elaniku tudengistaatus. Võimalikud väärtused: "active", "inactive".
- **university** string. *(mittekohustuslik)* Ülikool, mille nimekirjas elanik on.

.. code-block:: xml

    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:dor="http://www.ttu.ee/idu0075/2015/ws/dormitory">
       <soapenv:Header/>
       <soapenv:Body>
         <dor:getTenantListRequest>
             <dor:token>asd</dor:token>
             <!--Optional:-->
             <dor:university>TTU</dor:university>
          </dor:getTenantListRequest>
       </soapenv:Body>
    </soapenv:Envelope>

**Väljund:** getTenantListResponse

- **tenant** tenantType. *(0 või rohkem)* Elanik, kes on süsteemis registreeritud, omab **id** väärtust ja vastab päringus täpsustatud tingimustele.

.. code-block:: xml

    <S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
       <S:Body>
          <getTenantListResponse xmlns="http://www.ttu.ee/idu0075/2015/ws/dormitory">
             <tenant>
                <id>1</id>
                <firstName>Eva Maria</firstName>
                <lastName>Veitmaa</lastName>
                <idCode>49606064219</idCode>
                <gender>female</gender>
                <studentStatus>active</studentStatus>
                <university>TTU</university>
             </tenant>
             <tenant>
                <id>2</id>
                <firstName>John</firstName>
                <lastName>Smith</lastName>
                <idCode>39211040028</idCode>
                <gender>male</gender>
                <studentStatus>active</studentStatus>
                <university>TTU</university>
             </tenant>
             <tenant>
                <id>3</id>
                <firstName>Peter</firstName>
                <lastName>Griffin</lastName>
                <idCode>36108201573</idCode>
                <gender>male</gender>
                <studentStatus>active</studentStatus>
                <university>TTU</university>
             </tenant>
             <tenant>
                <id>4</id>
                <firstName>Mary</firstName>
                <lastName>Little</lastName>
                <idCode>49201040028</idCode>
                <gender>female</gender>
                <studentStatus>inactive</studentStatus>
                <university>TTU</university>
             </tenant>
          </getTenantListResponse>
       </S:Body>
    </S:Envelope>

addDormitoryTenant
------------------

Operatsioon elaniku lisamiseks ühiselamu nimekirja.

**Sisend:** addDormitoryTenantRequest

- **token** string. Kliendi autentimiseks kasutatav kood ehk *token*.
- **requestCode** integer. Päringu unikaalne identifikaator.
- **dormitoryId** integer. Viide ühiselamule, mille nimekirja elanik lisatakse.
- **tenantId** integer. Viide elanikule, kes vaadeldava ühiselamu nimekirja lisatakse.
- **startDate** date. Kuupäev, millest alates elanik ühiselamu nimekirja kuulub ehk lepingu alguskuupäev.
- **endDate** date. Kuupäev, millest alates elanik ühiselamu nimekirja enam ei kuulu ehk lepingu lõpukuupäev.
- **status** statusType. Kas leping on kehtiv või mitte.

.. code-block:: xml

    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:dor="http://www.ttu.ee/idu0075/2015/ws/dormitory">
       <soapenv:Header/>
       <soapenv:Body>
          <dor:addDormitoryTenantRequest>
             <dor:token>asd</dor:token>
             <dor:requestCode>1</dor:requestCode>
             <dor:dormitoryId>1</dor:dormitoryId>
             <dor:tenantId>1</dor:tenantId>
             <dor:startDate>2015-08-16</dor:startDate>
             <dor:endDate>2017-06-10</dor:endDate>
             <dor:status>active</dor:status>
          </dor:addDormitoryTenantRequest>
       </soapenv:Body>
    </soapenv:Envelope>

**Väljund:** addDormitoryTenantResponse

- **responseCode** integer. Päringust saadud unikaalne identifikaator, kontrollimaks *Idempotent Capability* mustrit.
- **dormitoryTenant** dormitoryTenantType. Viide vaadeldavale ühiselamule ja lisatud elanikule. Sisaldab elaniku ja päringus antud andmeid.

.. code-block:: xml

    <S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
       <S:Body>
          <addDormitoryTenantResponse xmlns="http://www.ttu.ee/idu0075/2015/ws/dormitory">
             <responseCode>1</responseCode>
             <dormitoryTenant>
                <tenant>
                   <id>1</id>
                   <firstName>Eva Maria</firstName>
                   <lastName>Veitmaa</lastName>
                   <idCode>49606064219</idCode>
                   <gender>female</gender>
                   <studentStatus>active</studentStatus>
                   <university>TTU</university>
                </tenant>
                <startDate>2015-08-16</startDate>
                <endDate>2017-06-10</endDate>
                <status>active</status>
             </dormitoryTenant>
          </addDormitoryTenantResponse>
       </S:Body>
    </S:Envelope>

getDormitoryTenant
------------------

Operatsioon ühele ühiselamuga seotud elanike nimekirja küsimiseks.

**Sisend:** getDormitoryTenantListRequest

- **token** string. Kliendi autentimiseks kasutatav kood ehk *token*.
- **dormitoryId** integer. Ühiselamu unikaalne identifikaator.

.. code-block:: xml

    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:dor="http://www.ttu.ee/idu0075/2015/ws/dormitory">
       <soapenv:Header/>
       <soapenv:Body>
          <dor:getDormitoryTenantListRequest>
             <dor:token>asd</dor:token>
             <dor:dormitoryId>1</dor:dormitoryId>
          </dor:getDormitoryTenantListRequest>
       </soapenv:Body>
    </soapenv:Envelope>

**Väljund:** getDormitoryTenantListResponse

- **getDormitoryTenantListResponse** dormitoryTenantListType. *(0 või rohkem)* Elanik, kes on süsteemis registreeritud, omab **id** väärtust ja on vaadeldava ühiselamu nimekirjas.

.. code-block:: xml

    <S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
       <S:Body>
          <getDormitoryTenantListResponse xmlns="http://www.ttu.ee/idu0075/2015/ws/dormitory">
             <dormitoryTenant>
                <tenant>
                   <id>1</id>
                   <firstName>Eva Maria</firstName>
                   <lastName>Veitmaa</lastName>
                   <idCode>49606064219</idCode>
                   <gender>female</gender>
                   <studentStatus>active</studentStatus>
                   <university>TTU</university>
                </tenant>
                <startDate>2015-08-16</startDate>
                <endDate>2017-06-10</endDate>
                <status>active</status>
             </dormitoryTenant>
             <dormitoryTenant>
                <tenant>
                   <id>2</id>
                   <firstName>John</firstName>
                   <lastName>Smith</lastName>
                   <idCode>39211040028</idCode>
                   <gender>male</gender>
                   <studentStatus>active</studentStatus>
                   <university>TTU</university>
                </tenant>
                <startDate>2014-11-01</startDate>
                <endDate>2017-06-10</endDate>
                <status>active</status>
             </dormitoryTenant>
          </getDormitoryTenantListResponse>
       </S:Body>
    </S:Envelope>




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

