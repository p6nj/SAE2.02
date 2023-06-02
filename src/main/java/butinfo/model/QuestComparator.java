package butinfo.model;

/**
 * Comparator of quests passed to the efficace function of Scenario to determine
 * how to choose between multiple available quests. Makes it possible to compose
 * multiple functions of graph traversal using only one backbone function.
 * 
 * @see Scenario#efficace1()
 */
interface QuestComparator {
    boolean compare(Quest current, Quest best, Quest candidate);
}
