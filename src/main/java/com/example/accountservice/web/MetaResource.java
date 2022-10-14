package com.example.accountservice.web;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetaResource {
    private int code;
    private String message;
    private IPaginationResource pages;

    public MetaResource(final int code, final String message, final IPaginationResource pages) {
        this.code = code;
        this.message = message;
        this.pages = pages;
    }

    public MetaResource() {
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public IPaginationResource getPages() {
        return this.pages;
    }

    public void setPages(final IPaginationResource pages) {
        this.pages = pages;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MetaResource)) {
            return false;
        } else {
            MetaResource other = (MetaResource) o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getCode() != other.getCode()) {
                return false;
            } else {
                Object this$message = this.getMessage();
                Object other$message = other.getMessage();
                if (this$message == null) {
                    if (other$message != null) {
                        return false;
                    }
                } else if (!this$message.equals(other$message)) {
                    return false;
                }

                Object this$pages = this.getPages();
                Object other$pages = other.getPages();
                if (this$pages == null) {
                    if (other$pages != null) {
                        return false;
                    }
                } else if (!this$pages.equals(other$pages)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MetaResource;
    }

    public int hashCode() {
        int result = 1;
        result = result * 59 + this.getCode();
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        Object $pages = this.getPages();
        result = result * 59 + ($pages == null ? 43 : $pages.hashCode());
        return result;
    }

    public String toString() {
        return "MetaResource(code=" + this.getCode() + ", message=" + this.getMessage() + ", pages=" + this.getPages() + ")";
    }
}

