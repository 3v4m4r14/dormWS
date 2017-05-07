====================================
DormitoryService API dokumentatsioon
====================================

**DormitoryService API** õvimaldab lisada ja pärida andmeid ühiselamu teenuselt. Ülemressursiks on Dormitory *(ühiselamu)* nind selle alamressursiks Tenant *(elanik)*.

Kõikidele klientidele on väljastatud ühine **token**, mis kehtib mõlema API puhul ning tuleb kaasa anda igale päringule. Vale tokeni korral väljastatakse veateade ning operatsioon ei õnnestu.

Selleks, et tuvastada topelt saadud päringuid, on kõik andmeid muutvad päringud varustatud identifikaatoriga, mis on ühe kliendi piires unikaalsed. Teist korda identse päringu saamisel tagastatakse eelnevalt koostatud vastus uuesti.

REST ja SOAP API kasutavad ühte ja sama veebiteenust ning kõik muudatused, mis ühe API kaudu tehakse, on nähtavad teises. Seetõttu on ka sisend- ja väljundandmed väga sarnased.
