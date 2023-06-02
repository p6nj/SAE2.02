package butinfo.model;

interface QuestComparator {
    Quest compare(Quest current, Quest best, Quest candidate);
}
