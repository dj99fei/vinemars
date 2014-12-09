// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: StarMessage.proto

package vine.app.message;

public final class StarMessage {
  private StarMessage() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface PostFeedbackOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // optional string userId = 1;
    /**
     * <code>optional string userId = 1;</code>
     *
     * <pre>
     *用户ID，游客为GUEST
     * </pre>
     */
    boolean hasUserId();
    /**
     * <code>optional string userId = 1;</code>
     *
     * <pre>
     *用户ID，游客为GUEST
     * </pre>
     */
    java.lang.String getUserId();
    /**
     * <code>optional string userId = 1;</code>
     *
     * <pre>
     *用户ID，游客为GUEST
     * </pre>
     */
    com.google.protobuf.ByteString
        getUserIdBytes();

    // optional string feedback = 2;
    /**
     * <code>optional string feedback = 2;</code>
     *
     * <pre>
     *反馈信息
     * </pre>
     */
    boolean hasFeedback();
    /**
     * <code>optional string feedback = 2;</code>
     *
     * <pre>
     *反馈信息
     * </pre>
     */
    java.lang.String getFeedback();
    /**
     * <code>optional string feedback = 2;</code>
     *
     * <pre>
     *反馈信息
     * </pre>
     */
    com.google.protobuf.ByteString
        getFeedbackBytes();

    // optional string contact = 3;
    /**
     * <code>optional string contact = 3;</code>
     *
     * <pre>
     *联系方式
     * </pre>
     */
    boolean hasContact();
    /**
     * <code>optional string contact = 3;</code>
     *
     * <pre>
     *联系方式
     * </pre>
     */
    java.lang.String getContact();
    /**
     * <code>optional string contact = 3;</code>
     *
     * <pre>
     *联系方式
     * </pre>
     */
    com.google.protobuf.ByteString
        getContactBytes();
  }
  /**
   * Protobuf type {@code PostFeedback}
   *
   * <pre>
   *用户反馈接口
   * </pre>
   */
  public static final class PostFeedback extends
      com.google.protobuf.GeneratedMessage
      implements PostFeedbackOrBuilder {
    // Use PostFeedback.newBuilder() to construct.
    private PostFeedback(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private PostFeedback(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final PostFeedback defaultInstance;
    public static PostFeedback getDefaultInstance() {
      return defaultInstance;
    }

    public PostFeedback getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private PostFeedback(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              bitField0_ |= 0x00000001;
              userId_ = input.readBytes();
              break;
            }
            case 18: {
              bitField0_ |= 0x00000002;
              feedback_ = input.readBytes();
              break;
            }
            case 26: {
              bitField0_ |= 0x00000004;
              contact_ = input.readBytes();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return vine.app.message.StarMessage.internal_static_PostFeedback_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return vine.app.message.StarMessage.internal_static_PostFeedback_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              vine.app.message.StarMessage.PostFeedback.class, vine.app.message.StarMessage.PostFeedback.Builder.class);
    }

    public static com.google.protobuf.Parser<PostFeedback> PARSER =
        new com.google.protobuf.AbstractParser<PostFeedback>() {
      public PostFeedback parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new PostFeedback(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<PostFeedback> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // optional string userId = 1;
    public static final int USERID_FIELD_NUMBER = 1;
    private java.lang.Object userId_;
    /**
     * <code>optional string userId = 1;</code>
     *
     * <pre>
     *用户ID，游客为GUEST
     * </pre>
     */
    public boolean hasUserId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional string userId = 1;</code>
     *
     * <pre>
     *用户ID，游客为GUEST
     * </pre>
     */
    public java.lang.String getUserId() {
      java.lang.Object ref = userId_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          userId_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string userId = 1;</code>
     *
     * <pre>
     *用户ID，游客为GUEST
     * </pre>
     */
    public com.google.protobuf.ByteString
        getUserIdBytes() {
      java.lang.Object ref = userId_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        userId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // optional string feedback = 2;
    public static final int FEEDBACK_FIELD_NUMBER = 2;
    private java.lang.Object feedback_;
    /**
     * <code>optional string feedback = 2;</code>
     *
     * <pre>
     *反馈信息
     * </pre>
     */
    public boolean hasFeedback() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional string feedback = 2;</code>
     *
     * <pre>
     *反馈信息
     * </pre>
     */
    public java.lang.String getFeedback() {
      java.lang.Object ref = feedback_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          feedback_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string feedback = 2;</code>
     *
     * <pre>
     *反馈信息
     * </pre>
     */
    public com.google.protobuf.ByteString
        getFeedbackBytes() {
      java.lang.Object ref = feedback_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        feedback_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // optional string contact = 3;
    public static final int CONTACT_FIELD_NUMBER = 3;
    private java.lang.Object contact_;
    /**
     * <code>optional string contact = 3;</code>
     *
     * <pre>
     *联系方式
     * </pre>
     */
    public boolean hasContact() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional string contact = 3;</code>
     *
     * <pre>
     *联系方式
     * </pre>
     */
    public java.lang.String getContact() {
      java.lang.Object ref = contact_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          contact_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string contact = 3;</code>
     *
     * <pre>
     *联系方式
     * </pre>
     */
    public com.google.protobuf.ByteString
        getContactBytes() {
      java.lang.Object ref = contact_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        contact_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private void initFields() {
      userId_ = "";
      feedback_ = "";
      contact_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBytes(1, getUserIdBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, getFeedbackBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, getContactBytes());
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, getUserIdBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, getFeedbackBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getContactBytes());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static vine.app.message.StarMessage.PostFeedback parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static vine.app.message.StarMessage.PostFeedback parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static vine.app.message.StarMessage.PostFeedback parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static vine.app.message.StarMessage.PostFeedback parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static vine.app.message.StarMessage.PostFeedback parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static vine.app.message.StarMessage.PostFeedback parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static vine.app.message.StarMessage.PostFeedback parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static vine.app.message.StarMessage.PostFeedback parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static vine.app.message.StarMessage.PostFeedback parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static vine.app.message.StarMessage.PostFeedback parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(vine.app.message.StarMessage.PostFeedback prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code PostFeedback}
     *
     * <pre>
     *用户反馈接口
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements vine.app.message.StarMessage.PostFeedbackOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return vine.app.message.StarMessage.internal_static_PostFeedback_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return vine.app.message.StarMessage.internal_static_PostFeedback_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                vine.app.message.StarMessage.PostFeedback.class, vine.app.message.StarMessage.PostFeedback.Builder.class);
      }

      // Construct using vine.app.message.StarMessage.PostFeedback.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        userId_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        feedback_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        contact_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return vine.app.message.StarMessage.internal_static_PostFeedback_descriptor;
      }

      public vine.app.message.StarMessage.PostFeedback getDefaultInstanceForType() {
        return vine.app.message.StarMessage.PostFeedback.getDefaultInstance();
      }

      public vine.app.message.StarMessage.PostFeedback build() {
        vine.app.message.StarMessage.PostFeedback result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public vine.app.message.StarMessage.PostFeedback buildPartial() {
        vine.app.message.StarMessage.PostFeedback result = new vine.app.message.StarMessage.PostFeedback(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.userId_ = userId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.feedback_ = feedback_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.contact_ = contact_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof vine.app.message.StarMessage.PostFeedback) {
          return mergeFrom((vine.app.message.StarMessage.PostFeedback)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(vine.app.message.StarMessage.PostFeedback other) {
        if (other == vine.app.message.StarMessage.PostFeedback.getDefaultInstance()) return this;
        if (other.hasUserId()) {
          bitField0_ |= 0x00000001;
          userId_ = other.userId_;
          onChanged();
        }
        if (other.hasFeedback()) {
          bitField0_ |= 0x00000002;
          feedback_ = other.feedback_;
          onChanged();
        }
        if (other.hasContact()) {
          bitField0_ |= 0x00000004;
          contact_ = other.contact_;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        vine.app.message.StarMessage.PostFeedback parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (vine.app.message.StarMessage.PostFeedback) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // optional string userId = 1;
      private java.lang.Object userId_ = "";
      /**
       * <code>optional string userId = 1;</code>
       *
       * <pre>
       *用户ID，游客为GUEST
       * </pre>
       */
      public boolean hasUserId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional string userId = 1;</code>
       *
       * <pre>
       *用户ID，游客为GUEST
       * </pre>
       */
      public java.lang.String getUserId() {
        java.lang.Object ref = userId_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          userId_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string userId = 1;</code>
       *
       * <pre>
       *用户ID，游客为GUEST
       * </pre>
       */
      public com.google.protobuf.ByteString
          getUserIdBytes() {
        java.lang.Object ref = userId_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          userId_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string userId = 1;</code>
       *
       * <pre>
       *用户ID，游客为GUEST
       * </pre>
       */
      public Builder setUserId(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        userId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string userId = 1;</code>
       *
       * <pre>
       *用户ID，游客为GUEST
       * </pre>
       */
      public Builder clearUserId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        userId_ = getDefaultInstance().getUserId();
        onChanged();
        return this;
      }
      /**
       * <code>optional string userId = 1;</code>
       *
       * <pre>
       *用户ID，游客为GUEST
       * </pre>
       */
      public Builder setUserIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        userId_ = value;
        onChanged();
        return this;
      }

      // optional string feedback = 2;
      private java.lang.Object feedback_ = "";
      /**
       * <code>optional string feedback = 2;</code>
       *
       * <pre>
       *反馈信息
       * </pre>
       */
      public boolean hasFeedback() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional string feedback = 2;</code>
       *
       * <pre>
       *反馈信息
       * </pre>
       */
      public java.lang.String getFeedback() {
        java.lang.Object ref = feedback_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          feedback_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string feedback = 2;</code>
       *
       * <pre>
       *反馈信息
       * </pre>
       */
      public com.google.protobuf.ByteString
          getFeedbackBytes() {
        java.lang.Object ref = feedback_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          feedback_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string feedback = 2;</code>
       *
       * <pre>
       *反馈信息
       * </pre>
       */
      public Builder setFeedback(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        feedback_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string feedback = 2;</code>
       *
       * <pre>
       *反馈信息
       * </pre>
       */
      public Builder clearFeedback() {
        bitField0_ = (bitField0_ & ~0x00000002);
        feedback_ = getDefaultInstance().getFeedback();
        onChanged();
        return this;
      }
      /**
       * <code>optional string feedback = 2;</code>
       *
       * <pre>
       *反馈信息
       * </pre>
       */
      public Builder setFeedbackBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        feedback_ = value;
        onChanged();
        return this;
      }

      // optional string contact = 3;
      private java.lang.Object contact_ = "";
      /**
       * <code>optional string contact = 3;</code>
       *
       * <pre>
       *联系方式
       * </pre>
       */
      public boolean hasContact() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional string contact = 3;</code>
       *
       * <pre>
       *联系方式
       * </pre>
       */
      public java.lang.String getContact() {
        java.lang.Object ref = contact_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          contact_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string contact = 3;</code>
       *
       * <pre>
       *联系方式
       * </pre>
       */
      public com.google.protobuf.ByteString
          getContactBytes() {
        java.lang.Object ref = contact_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          contact_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string contact = 3;</code>
       *
       * <pre>
       *联系方式
       * </pre>
       */
      public Builder setContact(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        contact_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string contact = 3;</code>
       *
       * <pre>
       *联系方式
       * </pre>
       */
      public Builder clearContact() {
        bitField0_ = (bitField0_ & ~0x00000004);
        contact_ = getDefaultInstance().getContact();
        onChanged();
        return this;
      }
      /**
       * <code>optional string contact = 3;</code>
       *
       * <pre>
       *联系方式
       * </pre>
       */
      public Builder setContactBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        contact_ = value;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:PostFeedback)
    }

    static {
      defaultInstance = new PostFeedback(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:PostFeedback)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_PostFeedback_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_PostFeedback_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021StarMessage.proto\"A\n\014PostFeedback\022\016\n\006u" +
      "serId\030\001 \001(\t\022\020\n\010feedback\030\002 \001(\t\022\017\n\007contact" +
      "\030\003 \001(\tB\022\n\020vine.app.message"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_PostFeedback_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_PostFeedback_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_PostFeedback_descriptor,
              new java.lang.String[] { "UserId", "Feedback", "Contact", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
