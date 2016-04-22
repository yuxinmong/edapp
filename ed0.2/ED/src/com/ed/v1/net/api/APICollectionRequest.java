package com.ed.v1.net.api;

import com.ed.v1.net.JSONDeserializable;

public class APICollectionRequest<T extends JSONDeserializable> extends APIRequest<T> {

    public static final int DEFAULT_PAGE_SIZE = 20;

    public static final class CollectionParams {
        public static final String START_INDEX = "startindex";
        public static final String PAGE = "page";
        public static final String PAGE_SIZE = "pageSize";
        public static final String SORT_BY = "sortBy";
        public static final String SORT_DIR = "sortDir";
        public static final String SORT_DIR_VALUE_ASC = "asc";
        public static final String SORT_DIR_VALUE_DESC = "desc";
    }

    public APICollectionRequest(String url, Class<T> cls) {
        super(url, cls);
        isCollectionRequest(true);
        setPageOptions(0, 0, 1);
    }

    public APIRequest<T> setResultSetSize(int resultSetSize) {
        return this.setPageOptions(0, 0, resultSetSize);
    }

    public APIRequest<T> setResultSetPages(int pages) {
        return this.setPageOptions(0, 0, pages * DEFAULT_PAGE_SIZE);
    }

    public APIRequest<T> setPageOptions(int startIndex, int page, int pageSize) {

        if (startIndex > 0) {
            this.addParameter(CollectionParams.START_INDEX, startIndex);
        }
        if (page > 0) {
            this.addParameter(CollectionParams.PAGE, page);

            if (pageSize == 0) {
                this.addParameter(CollectionParams.PAGE_SIZE, APICollectionRequest.DEFAULT_PAGE_SIZE);
            } else {
                this.addParameter(CollectionParams.PAGE_SIZE, pageSize);
            }
        } else if (pageSize > 0) {
            this.addParameter(CollectionParams.PAGE, 0);
            this.addParameter(CollectionParams.PAGE_SIZE, pageSize);
        }

        return this;
    }

    public APIRequest<T> setOrderOptions(String sortBy, boolean ascending) {
        if (sortBy != null && sortBy.length() > 0) {
            this.addParameter(CollectionParams.SORT_BY, sortBy);

            if (ascending) {
                this.addParameter(CollectionParams.SORT_DIR, CollectionParams.SORT_DIR_VALUE_ASC);
            } else {
                this.addParameter(CollectionParams.SORT_DIR, CollectionParams.SORT_DIR_VALUE_DESC);
            }
        }

        return this;
    }

}
