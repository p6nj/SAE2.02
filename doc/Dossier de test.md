# Dossier de tests
## Sommaire<!-- omit in toc -->
- [Dossier de tests](#dossier-de-tests)
  - [1. Introduction](#1-introduction)
  - [2. Description de la procédure de test](#2-description-de-la-procédure-de-test)
  - [3. Description des informations à enregistrer pour le test](#3-description-des-informations-à-enregistrer-pour-le-test)
    - [1. Campagne de test](#1-campagne-de-test)
    - [2. Tests](#2-tests)
      - [1. Test 1](#1-test-1)
        - [Partitions de test](#partitions-de-test)
        - [Données de test](#données-de-test)
      - [2. Test 2](#2-test-2)
        - [Partitions de test](#partitions-de-test-1)
        - [Données de test](#données-de-test-1)
      - [3. Test 3](#3-test-3)
        - [Partitions de test](#partitions-de-test-2)
        - [Données de test](#données-de-test-2)
      - [4. Test 4](#4-test-4)
        - [Partitions de test](#partitions-de-test-3)
        - [Données de test](#données-de-test-3)
    - [3. Résultats](#3-résultats)
    - [4. Conclusions](#4-conclusions)
  
## 1. Introduction
Ce document est dossier de tests qui a pour objectif d’expliquer les tests que l’on a fait durant la SAé 2.02.

## 2. Description de la procédure de test
Pour les tests, nous avons décidé d'utiliser des tests de boîtes blanches dans le but de vérifier si la réponse obtenue correspond bien à celle que nous avons prévue pour chaque situation pouvant se présenter lors de l'exécution du programme. Si le programme produit la réponse attendue pour chaque situation possible, théoriquement, il fonctionnera pour n'importe quel scénario que nous lui soumettrons.

## 3. Description des informations à enregistrer pour le test
Les tests s'effecturont via l'extension Test Explorer UI de Visual Studio Code. Les résultats seront visibles dans l'onglet du side pane de VSCode.

### 1. Campagne de test
| Contexte                             |                                                                                         |
| :----------------------------------- | :-------------------------------------------------------------------------------------- |
| Configuration logicielle :           | Visual Studio 1.74, Java Extension Pack 0.9, OpenJDK 11, Ubuntu 22.04 LTS               |
| Configuration matérielle :           | AMD® Ryzen 5 5600 × 6, RTX 3070 ti, 32GiB RAM                                           |
| Date de début :  4/06/2023           | Date de finalisation : semaine du 6/02/2023                                             |
| Tests à appliquer :                  | Test sur la méthode accessible, efficace, exhaustive et sur le système de sérialisation |
| Responsable de la campagne de test : | Elias Moussamih                                                                         |

### 2. Tests
#### 1. Test 1
|                         |                                                                              |
| :---------------------- | :--------------------------------------------------------------------------- |
| Identification : access | Version : 1.0                                                                |
| Description :           | test sur la méthode accessible en utilisant la stratégie des boites blanches |
| Resesources requises :  | Ordinateur, visual studio code, méthode accessible et ses dépendances        |
| Responsable :           | Elias Moussamih                                                              |

##### Partitions de test
| Antécédents respectés | Quête 0 | XP respecté | Résultats attendus |
| :-------------------- | :------ | :---------- | :----------------- |
| oui                   | non     | /           | vrai               |
| non                   | non     | /           | faux               |
| pas d'antécédents     | non     | /           | vrai               |
| oui                   | oui     | oui         | vrai               |
| oui                   | oui     | non         | faux               |
| non                   | oui     | non         | faux               |
| non                   | oui     | oui         | faux               |

##### Données de test
| Quêtes effectués | Quête actuelle | XP nécessaire | XP atteint | Antécédent(s) de la quête | Résultats Attendus |
| :--------------- | :------------- | :------------ | :--------- | :------------------------ | :----------------- |
| 1,2              | 3              | /             | /          | 2                         | vrai               |
| 1,2              | 4              | /             | /          | 3                         | faux               |
| /                | 1              | /             | /          | /                         | vrai               |
| 1,2,3,4          | 0              | 100           | 200        | 4                         | vrai               |
| 4                | 0              | 100           | 50         | 4                         | faux               |
| 1                | 0              | 100           | 50         | 4                         | faux               |
| 1,2,3            | 0              | 100           | 150        | 4                         | faux               |

---
#### 2. Test 2
|                          |                                                                                                     |
| :----------------------- | :-------------------------------------------------------------------------------------------------- |
| Identification : exhaust | Version : 1.0                                                                                       |
| Description :            | test sur la méthode exhaustive en utilisant la stratégie des boites blanches, passe si pas d'erreur |
| Resesources requises :   | Ordinateur, visual studio code, méthode exhaustive ainsi et ses dépendances                         |
| Responsable :            | Elias Moussamih                                                                                     |

##### Partitions de test
| Scénario | erreur |
| :------- | :----- |
| S        | non    |

##### Données de test
| Scénario | erreur |
| :------- | :----- |
| S0       | non    |
| S1       | non    |
| S2       | non    |
| S3       | non    |
| S4       | non    |
| S5       | non    |
| S6       | non    |
| S7       | non    |
| S8       | non    |
| S9       | non    |
| S10      | non    |

---
#### 3. Test 3
|                        |                                                                            |
| :--------------------- | :------------------------------------------------------------------------- |
| Identification : effic | Version : 1.0                                                              |
| Description :          | test sur la méthode efficace en utilisant la stratégie des boites blanches |
| Resesources requises : | Ordinateur, visual studio code, méthode efficace et ses dépendances        |
| Responsable :          | Elias Moussamih                                                            |

##### Partitions de test
| Type de scénario                                                                                                                                                                     | Chemin attendu |
| :----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :------------- |
| Scénario sans choix (<u>C</u>->B, B->A)                                                                                                                                              | ABC            |
| Scénario avec choix (<u>D</u>->C, <u>D</u>->B, C->A, B->A, avec ABD meilleur que ACD en terme de distance)                                                                           | ABD            |
| Scénario sans choix avec compensation d'XP nécessaire (<u>C</u>->B, B->A, D avec D nécessaire à C question XP)                                                                       | ABDC           |
| Scénario avec choix et compensation d'XP nécessaire (<u>E</u>->D, <u>E</u>->C, D->B, C->B, B->A, D avec D nécessaire à E question XP et ABCE meilleur que ABDE en terme de distance) | ABCDE          |

##### Données de test
Soit le scénario S$\alpha$ défini par ces antécédents: <u>C</u>->B, B->A.
Soit le scénario S$\beta$ défini par ces antécédents: <u>C</u>->B, B->A, D avec D nécessaire à C.
| Scénario  | Chemin attendu |
| :-------- | :------------- |
| S$\alpha$ | ABC            |
| S0        | 1240           |
| S$\beta$  | ABDC           |
| S1        | 143250         |


---
#### 4. Test 4
|                        |                                                                                |
| :--------------------- | :----------------------------------------------------------------------------- |
| Identification : scen  | Version : 1.0                                                                  |
| Description :          | test sur les méthodes de sérialisation en utilisant la stratégie boîte noire   |
| Resesources requises : | Ordinateur, visual studio code, méthodes de sérialisation et leurs dépendances |
| Responsable :          | Breval Ferrari                                                                 |

##### Partitions de test
| Scénario | contenu du fichier == scénario sérialisé |
| :------- | :--------------------------------------- |
| S        | oui                                      |

##### Données de test
| Scénario | contenu du fichier == scénario sérialisé |
| :------- | :--------------------------------------- |
| S0       | oui                                      |
| S1       | oui                                      |
| S2       | oui                                      |
| S3       | oui                                      |
| S4       | oui                                      |
| S5       | oui                                      |
| S6       | oui                                      |
| S7       | oui                                      |
| S8       | oui                                      |
| S9       | oui                                      |
| S10      | oui                                      |

### 3. Résultats
| Test 1                       |                |
| :--------------------------- | :------------- |
| Référence du test appliqué : | d              |
| Responsable :                | Breval Ferrari |
| Date d'application :         | d              |
| Résultat :                   | d              |
| Occurences des résultats :   | d              |

### 4. Conclusions
uh
