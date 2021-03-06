// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/com/jetucker/Response.proto

package com.jetucker;

public final class Response {
  private Response() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  /**
   * Protobuf enum {@code com.jetucker.ResponseControlCode}
   */
  public enum ResponseControlCode
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>RESPONSE_AUTH = 1;</code>
     */
    RESPONSE_AUTH(0, 1),
    /**
     * <code>RESPONSE_FILE = 2;</code>
     */
    RESPONSE_FILE(1, 2),
    /**
     * <code>RESPONSE_ERROR = 3;</code>
     */
    RESPONSE_ERROR(2, 3),
    ;

    /**
     * <code>RESPONSE_AUTH = 1;</code>
     */
    public static final int RESPONSE_AUTH_VALUE = 1;
    /**
     * <code>RESPONSE_FILE = 2;</code>
     */
    public static final int RESPONSE_FILE_VALUE = 2;
    /**
     * <code>RESPONSE_ERROR = 3;</code>
     */
    public static final int RESPONSE_ERROR_VALUE = 3;


    public final int getNumber() { return value; }

    public static ResponseControlCode valueOf(int value) {
      switch (value) {
        case 1: return RESPONSE_AUTH;
        case 2: return RESPONSE_FILE;
        case 3: return RESPONSE_ERROR;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<ResponseControlCode>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<ResponseControlCode>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<ResponseControlCode>() {
            public ResponseControlCode findValueByNumber(int number) {
              return ResponseControlCode.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.jetucker.Response.getDescriptor().getEnumTypes().get(0);
    }

    private static final ResponseControlCode[] VALUES = values();

    public static ResponseControlCode valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private ResponseControlCode(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:com.jetucker.ResponseControlCode)
  }

  public interface ControlResponseOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.jetucker.ControlResponse)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int64 userId = 1;</code>
     */
    boolean hasUserId();
    /**
     * <code>required int64 userId = 1;</code>
     */
    long getUserId();

    /**
     * <code>required .com.jetucker.ResponseControlCode controlCode = 2;</code>
     */
    boolean hasControlCode();
    /**
     * <code>required .com.jetucker.ResponseControlCode controlCode = 2;</code>
     */
    com.jetucker.Response.ResponseControlCode getControlCode();

    /**
     * <code>optional string errorMsg = 3;</code>
     */
    boolean hasErrorMsg();
    /**
     * <code>optional string errorMsg = 3;</code>
     */
    java.lang.String getErrorMsg();
    /**
     * <code>optional string errorMsg = 3;</code>
     */
    com.google.protobuf.ByteString
        getErrorMsgBytes();

    /**
     * <code>optional bool isAuthenticated = 4;</code>
     */
    boolean hasIsAuthenticated();
    /**
     * <code>optional bool isAuthenticated = 4;</code>
     */
    boolean getIsAuthenticated();

    /**
     * <code>optional string fileName = 5;</code>
     */
    boolean hasFileName();
    /**
     * <code>optional string fileName = 5;</code>
     */
    java.lang.String getFileName();
    /**
     * <code>optional string fileName = 5;</code>
     */
    com.google.protobuf.ByteString
        getFileNameBytes();

    /**
     * <code>optional bytes fileContents = 6;</code>
     */
    boolean hasFileContents();
    /**
     * <code>optional bytes fileContents = 6;</code>
     */
    com.google.protobuf.ByteString getFileContents();
  }
  /**
   * Protobuf type {@code com.jetucker.ControlResponse}
   */
  public static final class ControlResponse extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:com.jetucker.ControlResponse)
      ControlResponseOrBuilder {
    // Use ControlResponse.newBuilder() to construct.
    private ControlResponse(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private ControlResponse(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final ControlResponse defaultInstance;
    public static ControlResponse getDefaultInstance() {
      return defaultInstance;
    }

    public ControlResponse getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private ControlResponse(
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
            case 8: {
              bitField0_ |= 0x00000001;
              userId_ = input.readInt64();
              break;
            }
            case 16: {
              int rawValue = input.readEnum();
              com.jetucker.Response.ResponseControlCode value = com.jetucker.Response.ResponseControlCode.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(2, rawValue);
              } else {
                bitField0_ |= 0x00000002;
                controlCode_ = value;
              }
              break;
            }
            case 26: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000004;
              errorMsg_ = bs;
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              isAuthenticated_ = input.readBool();
              break;
            }
            case 42: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000010;
              fileName_ = bs;
              break;
            }
            case 50: {
              bitField0_ |= 0x00000020;
              fileContents_ = input.readBytes();
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
      return com.jetucker.Response.internal_static_com_jetucker_ControlResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.jetucker.Response.internal_static_com_jetucker_ControlResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.jetucker.Response.ControlResponse.class, com.jetucker.Response.ControlResponse.Builder.class);
    }

    public static com.google.protobuf.Parser<ControlResponse> PARSER =
        new com.google.protobuf.AbstractParser<ControlResponse>() {
      public ControlResponse parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ControlResponse(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<ControlResponse> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int USERID_FIELD_NUMBER = 1;
    private long userId_;
    /**
     * <code>required int64 userId = 1;</code>
     */
    public boolean hasUserId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int64 userId = 1;</code>
     */
    public long getUserId() {
      return userId_;
    }

    public static final int CONTROLCODE_FIELD_NUMBER = 2;
    private com.jetucker.Response.ResponseControlCode controlCode_;
    /**
     * <code>required .com.jetucker.ResponseControlCode controlCode = 2;</code>
     */
    public boolean hasControlCode() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required .com.jetucker.ResponseControlCode controlCode = 2;</code>
     */
    public com.jetucker.Response.ResponseControlCode getControlCode() {
      return controlCode_;
    }

    public static final int ERRORMSG_FIELD_NUMBER = 3;
    private java.lang.Object errorMsg_;
    /**
     * <code>optional string errorMsg = 3;</code>
     */
    public boolean hasErrorMsg() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional string errorMsg = 3;</code>
     */
    public java.lang.String getErrorMsg() {
      java.lang.Object ref = errorMsg_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          errorMsg_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string errorMsg = 3;</code>
     */
    public com.google.protobuf.ByteString
        getErrorMsgBytes() {
      java.lang.Object ref = errorMsg_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        errorMsg_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int ISAUTHENTICATED_FIELD_NUMBER = 4;
    private boolean isAuthenticated_;
    /**
     * <code>optional bool isAuthenticated = 4;</code>
     */
    public boolean hasIsAuthenticated() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional bool isAuthenticated = 4;</code>
     */
    public boolean getIsAuthenticated() {
      return isAuthenticated_;
    }

    public static final int FILENAME_FIELD_NUMBER = 5;
    private java.lang.Object fileName_;
    /**
     * <code>optional string fileName = 5;</code>
     */
    public boolean hasFileName() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>optional string fileName = 5;</code>
     */
    public java.lang.String getFileName() {
      java.lang.Object ref = fileName_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          fileName_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string fileName = 5;</code>
     */
    public com.google.protobuf.ByteString
        getFileNameBytes() {
      java.lang.Object ref = fileName_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        fileName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int FILECONTENTS_FIELD_NUMBER = 6;
    private com.google.protobuf.ByteString fileContents_;
    /**
     * <code>optional bytes fileContents = 6;</code>
     */
    public boolean hasFileContents() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <code>optional bytes fileContents = 6;</code>
     */
    public com.google.protobuf.ByteString getFileContents() {
      return fileContents_;
    }

    private void initFields() {
      userId_ = 0L;
      controlCode_ = com.jetucker.Response.ResponseControlCode.RESPONSE_AUTH;
      errorMsg_ = "";
      isAuthenticated_ = false;
      fileName_ = "";
      fileContents_ = com.google.protobuf.ByteString.EMPTY;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasUserId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasControlCode()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt64(1, userId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeEnum(2, controlCode_.getNumber());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, getErrorMsgBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeBool(4, isAuthenticated_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeBytes(5, getFileNameBytes());
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        output.writeBytes(6, fileContents_);
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
          .computeInt64Size(1, userId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(2, controlCode_.getNumber());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getErrorMsgBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(4, isAuthenticated_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(5, getFileNameBytes());
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(6, fileContents_);
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

    public static com.jetucker.Response.ControlResponse parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.jetucker.Response.ControlResponse parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.jetucker.Response.ControlResponse parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.jetucker.Response.ControlResponse parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.jetucker.Response.ControlResponse parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.jetucker.Response.ControlResponse parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.jetucker.Response.ControlResponse parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.jetucker.Response.ControlResponse parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.jetucker.Response.ControlResponse parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.jetucker.Response.ControlResponse parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.jetucker.Response.ControlResponse prototype) {
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
     * Protobuf type {@code com.jetucker.ControlResponse}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.jetucker.ControlResponse)
        com.jetucker.Response.ControlResponseOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.jetucker.Response.internal_static_com_jetucker_ControlResponse_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.jetucker.Response.internal_static_com_jetucker_ControlResponse_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.jetucker.Response.ControlResponse.class, com.jetucker.Response.ControlResponse.Builder.class);
      }

      // Construct using com.jetucker.Response.ControlResponse.newBuilder()
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
        userId_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000001);
        controlCode_ = com.jetucker.Response.ResponseControlCode.RESPONSE_AUTH;
        bitField0_ = (bitField0_ & ~0x00000002);
        errorMsg_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        isAuthenticated_ = false;
        bitField0_ = (bitField0_ & ~0x00000008);
        fileName_ = "";
        bitField0_ = (bitField0_ & ~0x00000010);
        fileContents_ = com.google.protobuf.ByteString.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000020);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.jetucker.Response.internal_static_com_jetucker_ControlResponse_descriptor;
      }

      public com.jetucker.Response.ControlResponse getDefaultInstanceForType() {
        return com.jetucker.Response.ControlResponse.getDefaultInstance();
      }

      public com.jetucker.Response.ControlResponse build() {
        com.jetucker.Response.ControlResponse result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.jetucker.Response.ControlResponse buildPartial() {
        com.jetucker.Response.ControlResponse result = new com.jetucker.Response.ControlResponse(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.userId_ = userId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.controlCode_ = controlCode_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.errorMsg_ = errorMsg_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.isAuthenticated_ = isAuthenticated_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.fileName_ = fileName_;
        if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
          to_bitField0_ |= 0x00000020;
        }
        result.fileContents_ = fileContents_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.jetucker.Response.ControlResponse) {
          return mergeFrom((com.jetucker.Response.ControlResponse)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.jetucker.Response.ControlResponse other) {
        if (other == com.jetucker.Response.ControlResponse.getDefaultInstance()) return this;
        if (other.hasUserId()) {
          setUserId(other.getUserId());
        }
        if (other.hasControlCode()) {
          setControlCode(other.getControlCode());
        }
        if (other.hasErrorMsg()) {
          bitField0_ |= 0x00000004;
          errorMsg_ = other.errorMsg_;
          onChanged();
        }
        if (other.hasIsAuthenticated()) {
          setIsAuthenticated(other.getIsAuthenticated());
        }
        if (other.hasFileName()) {
          bitField0_ |= 0x00000010;
          fileName_ = other.fileName_;
          onChanged();
        }
        if (other.hasFileContents()) {
          setFileContents(other.getFileContents());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasUserId()) {
          
          return false;
        }
        if (!hasControlCode()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.jetucker.Response.ControlResponse parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.jetucker.Response.ControlResponse) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private long userId_ ;
      /**
       * <code>required int64 userId = 1;</code>
       */
      public boolean hasUserId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int64 userId = 1;</code>
       */
      public long getUserId() {
        return userId_;
      }
      /**
       * <code>required int64 userId = 1;</code>
       */
      public Builder setUserId(long value) {
        bitField0_ |= 0x00000001;
        userId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int64 userId = 1;</code>
       */
      public Builder clearUserId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        userId_ = 0L;
        onChanged();
        return this;
      }

      private com.jetucker.Response.ResponseControlCode controlCode_ = com.jetucker.Response.ResponseControlCode.RESPONSE_AUTH;
      /**
       * <code>required .com.jetucker.ResponseControlCode controlCode = 2;</code>
       */
      public boolean hasControlCode() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required .com.jetucker.ResponseControlCode controlCode = 2;</code>
       */
      public com.jetucker.Response.ResponseControlCode getControlCode() {
        return controlCode_;
      }
      /**
       * <code>required .com.jetucker.ResponseControlCode controlCode = 2;</code>
       */
      public Builder setControlCode(com.jetucker.Response.ResponseControlCode value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000002;
        controlCode_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required .com.jetucker.ResponseControlCode controlCode = 2;</code>
       */
      public Builder clearControlCode() {
        bitField0_ = (bitField0_ & ~0x00000002);
        controlCode_ = com.jetucker.Response.ResponseControlCode.RESPONSE_AUTH;
        onChanged();
        return this;
      }

      private java.lang.Object errorMsg_ = "";
      /**
       * <code>optional string errorMsg = 3;</code>
       */
      public boolean hasErrorMsg() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional string errorMsg = 3;</code>
       */
      public java.lang.String getErrorMsg() {
        java.lang.Object ref = errorMsg_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            errorMsg_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string errorMsg = 3;</code>
       */
      public com.google.protobuf.ByteString
          getErrorMsgBytes() {
        java.lang.Object ref = errorMsg_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          errorMsg_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string errorMsg = 3;</code>
       */
      public Builder setErrorMsg(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        errorMsg_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string errorMsg = 3;</code>
       */
      public Builder clearErrorMsg() {
        bitField0_ = (bitField0_ & ~0x00000004);
        errorMsg_ = getDefaultInstance().getErrorMsg();
        onChanged();
        return this;
      }
      /**
       * <code>optional string errorMsg = 3;</code>
       */
      public Builder setErrorMsgBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        errorMsg_ = value;
        onChanged();
        return this;
      }

      private boolean isAuthenticated_ ;
      /**
       * <code>optional bool isAuthenticated = 4;</code>
       */
      public boolean hasIsAuthenticated() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional bool isAuthenticated = 4;</code>
       */
      public boolean getIsAuthenticated() {
        return isAuthenticated_;
      }
      /**
       * <code>optional bool isAuthenticated = 4;</code>
       */
      public Builder setIsAuthenticated(boolean value) {
        bitField0_ |= 0x00000008;
        isAuthenticated_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional bool isAuthenticated = 4;</code>
       */
      public Builder clearIsAuthenticated() {
        bitField0_ = (bitField0_ & ~0x00000008);
        isAuthenticated_ = false;
        onChanged();
        return this;
      }

      private java.lang.Object fileName_ = "";
      /**
       * <code>optional string fileName = 5;</code>
       */
      public boolean hasFileName() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      /**
       * <code>optional string fileName = 5;</code>
       */
      public java.lang.String getFileName() {
        java.lang.Object ref = fileName_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            fileName_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string fileName = 5;</code>
       */
      public com.google.protobuf.ByteString
          getFileNameBytes() {
        java.lang.Object ref = fileName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          fileName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string fileName = 5;</code>
       */
      public Builder setFileName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
        fileName_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string fileName = 5;</code>
       */
      public Builder clearFileName() {
        bitField0_ = (bitField0_ & ~0x00000010);
        fileName_ = getDefaultInstance().getFileName();
        onChanged();
        return this;
      }
      /**
       * <code>optional string fileName = 5;</code>
       */
      public Builder setFileNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
        fileName_ = value;
        onChanged();
        return this;
      }

      private com.google.protobuf.ByteString fileContents_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>optional bytes fileContents = 6;</code>
       */
      public boolean hasFileContents() {
        return ((bitField0_ & 0x00000020) == 0x00000020);
      }
      /**
       * <code>optional bytes fileContents = 6;</code>
       */
      public com.google.protobuf.ByteString getFileContents() {
        return fileContents_;
      }
      /**
       * <code>optional bytes fileContents = 6;</code>
       */
      public Builder setFileContents(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000020;
        fileContents_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional bytes fileContents = 6;</code>
       */
      public Builder clearFileContents() {
        bitField0_ = (bitField0_ & ~0x00000020);
        fileContents_ = getDefaultInstance().getFileContents();
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:com.jetucker.ControlResponse)
    }

    static {
      defaultInstance = new ControlResponse(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:com.jetucker.ControlResponse)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_jetucker_ControlResponse_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_jetucker_ControlResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\037src/com/jetucker/Response.proto\022\014com.j" +
      "etucker\"\254\001\n\017ControlResponse\022\016\n\006userId\030\001 " +
      "\002(\003\0226\n\013controlCode\030\002 \002(\0162!.com.jetucker." +
      "ResponseControlCode\022\020\n\010errorMsg\030\003 \001(\t\022\027\n" +
      "\017isAuthenticated\030\004 \001(\010\022\020\n\010fileName\030\005 \001(\t" +
      "\022\024\n\014fileContents\030\006 \001(\014*O\n\023ResponseContro" +
      "lCode\022\021\n\rRESPONSE_AUTH\020\001\022\021\n\rRESPONSE_FIL" +
      "E\020\002\022\022\n\016RESPONSE_ERROR\020\003B\nB\010Response"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_jetucker_ControlResponse_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_jetucker_ControlResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_com_jetucker_ControlResponse_descriptor,
        new java.lang.String[] { "UserId", "ControlCode", "ErrorMsg", "IsAuthenticated", "FileName", "FileContents", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
