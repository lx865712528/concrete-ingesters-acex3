package edu.stanford.nlp.ie.machinereading.domains.ace.reader;

import edu.stanford.nlp.ie.machinereading.domains.ace.reader.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Stores one ACE event mention
 */
public class AceEventMention extends AceMention {

    /**
     * the roles of argument mentions
     */
    private List<String> mRoles;

    /**
     * the argument mentions
     */
    private List<AceEventMentionArgument> mArguments;

    /**
     * the parent event
     */
    private AceEvent mParent;

    /**
     * anchor text for this event
     */
    private AceCharSeq mAnchor;

    public AceEventMention(String id, AceCharSeq extent, AceCharSeq anchor) {
        super(id, extent);
        this.mArguments = new ArrayList<AceEventMentionArgument>();
        this.mRoles = new ArrayList<String>();
        this.mAnchor = anchor;
    }

    @Override
    public String toString() {
        return "AceEventMention [mAnchor=" + mAnchor + ", mParent=" + mParent
                + ", mRoles=" + mRoles + ", mArguments=" + mArguments
                + ", mExtent=" + mExtent + ", mId=" + mId + "]";
    }

    public Collection<AceEventMentionArgument> getArgs() {
        return mArguments;
    }

    public Collection<String> getRoles() {
        return mRoles;
    }

    public AceEntityMention getArg(AceEventMentionArgument argument) {
        return argument.getContent();
    }

    public void addArg(AceEntityMention em, String role) {
        mRoles.add(role);
        mArguments.add(new AceEventMentionArgument(role, em));
    }

    public void setParent(AceEvent e) {
        mParent = e;
    }

    public AceEvent getParent() {
        return mParent;
    }

    public void setAnchor(AceCharSeq anchor) {
        mAnchor = anchor;
    }

    public AceCharSeq getAnchor() {
        return mAnchor;
    }

    /** Fetches the id of the sentence that contains this mention */
    // TODO disabled until we tie in sentence boundaries
    // public int getSentence(AceDocument doc) {
    // return doc.getToken(getArg(0).getHead().getTokenStart()).getSentence();
    // }

    /**
     * Returns the smallest start of all argument heads (or the beginning of the
     * mention's extent if there are no arguments)
     */
    public int getMinTokenStart() {
        Collection<AceEventMentionArgument> args = getArgs();
        int earliestTokenStart = -1;
        for (AceEventMentionArgument arg : args) {
            int tokenStart = arg.getContent().getHead().getTokenStart();
            if (earliestTokenStart == -1)
                earliestTokenStart = tokenStart;
            else
                earliestTokenStart = Math.min(earliestTokenStart, tokenStart);
        }

        // this will happen when we have no arguments
        if (earliestTokenStart == -1)
            return mExtent.getTokenStart();

        return earliestTokenStart;
    }

    /**
     * Returns the largest start of all argument heads (or the beginning of the
     * mention's extent if there are no arguments)
     */
    public int getMaxTokenEnd() {
        Collection<AceEventMentionArgument> args = getArgs();
        int latestTokenStart = -1;
        for (AceEventMentionArgument arg : args) {
            int tokenStart = arg.getContent().getHead().getTokenStart();
            if (latestTokenStart == -1)
                latestTokenStart = tokenStart;
            else
                latestTokenStart = Math.max(latestTokenStart, tokenStart);
        }

        // this will happen when we have no arguments
        if (latestTokenStart == -1)
            return mExtent.getTokenStart();

        return latestTokenStart;
    }

    // TODO: toXml method
}
