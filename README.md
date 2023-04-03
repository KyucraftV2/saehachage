# SAE 1.02
## Hachage

Contexte (extrait du sujet disponible à la racine du projet) :

Les fonctions de hachage sont un outil classique de l’algorithmique², dont une des principales application
est la suivante. On souhaite créer une structure de donnée T (pour table) pour y stocker un ensemble
d’objets S ⊆ U, dans un contexte où |S| est très petit devant |U|. Typiquement:
* **(Exemple 1)** S représente l’ensemble des mots utilisés dans un texte donné, et U est l’ensemble
des chaînes d’au plus 30 caractères (en considérant que tous les mots utilisés dans le texte sont de
longueur au plus 30).
* **(Exemple 2)** S représente un ensemble de positions de sudoku 9x9 déjà rencontrées par un algorithme
d’exploration de type "brute force" (chaque élément de S est une matrice de 9 × 9 remplie d’entiers
entre 1 et 9), et U est l’ensemble de toutes les matrices 9 × 9 à coefficients entre 1 et 9.
* **(Exemple 3)** S représente l’ensemble des adresses IP des personnes ayant visité un site web donné,
et U représente toutes les adresses IP possibles.

On aimerait définir T pour avoir les deux propriétés suivantes :
* **(Propriété p1)** on voudrait que la taille totale utilisée par la table |T| ne soit pas trop grande par
rapport au nombre d’éléments |S| à stocker : idéalement |T| ≤ c|S|, ou même |T| ≤ c|S², avec c une petite constante, mais surtout pas |T| proche de |U|
* **(Propriété p2)** on voudrait que les opérations de base (insertion, suppression, recherche) soient très
rapides, au sens où elles n’exécutent que très peu d’opérations (par exemple un nombre constant,
c’est-à-dire borné par une certaine constante, ou logarithmique en |T|).

On considérera dans ce sujet l’exemple 1, qui est un cas dit statique dans lequel on connaît S à l’avance.
L’objectif est donc d’écrire un algorithme qui, étant donné un fichier de texte en entrée, construit une
table T ayant si possible les propriétés p1 et p2. Cette table sera alors soumise à un très grand nombre de
recherches, l’objectif étant de minimiser le temps total de ces recherches (grâce à la propriété p2), tout en
garantissant un temps de construction et un espace mémoire utilisé raisonnable (grâce à la propriété p1).
